package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CampaignServiceTest {

    @Mock
    private static CampaignRepository campaignRepository;
    @Mock
    private static CampaignMapper campaignMapper;
    private static  CampaignService campaignService;
    private AutoCloseable openMocks;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        campaignService = new CampaignService(campaignRepository, campaignMapper);
    }

    @AfterEach
    public void tearDown() throws Exception {
        validateMockitoUsage();
        openMocks.close();
    }
    @Test
    public void getCampaign_shouldReturnCampaign_idProvided() throws CampaignNotExistsException {
        Long id = 1L;

        when(campaignRepository.findById(id)).thenReturn(Optional.of(new Campaign()));
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(new CampaignDto());

        CampaignDto result = campaignService.getCampaign(id);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getClass(), CampaignDto.class);
            verify(campaignRepository, times(1)).findById(id);
            verify(campaignMapper, times(1)).campaignToDto(any(Campaign.class));
                }
        );
    }

    @Test
    public void getCampaign_shouldThrowCampaignNotExistsException_notExistingIdProvided() {
        Long id = 1L;

        when(campaignRepository.findById(id)).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(CampaignNotExistsException.class, () -> campaignService.getCampaign(id));
            verify(campaignRepository, times(1)).findById(id);
        });
    }

    @Test
    public void getAllCampaigns_shouldReturnPageOfCampaignsDto() {
        Pageable pageable = PageRequest.of(1, 2);
        when(campaignRepository.findAll(any(Pageable.class))).thenReturn(Page.empty(pageable));

        Page<CampaignDto> result = campaignService.getAllCampaigns(1, 2);

        assertAll(() -> {
            assertEquals(0, result.getTotalElements());
            verify(campaignRepository, times(1)).findAll(any(Pageable.class));
        });
    }


    @Test
    public void createCampaign_shouldCreateCampaign() {
        CampaignDto input = new CampaignDto();

        Campaign expected = new Campaign();
        expected.setCampaignName("test");

        CampaignDto expectedDto = new CampaignDto();

        when(campaignMapper.dtoToCampaign(input)).thenReturn(expected);
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(expectedDto);
        when(campaignRepository.save(any())).thenReturn(expected);
        CampaignDto result = campaignService.createCampaign(input);

        assertAll(() -> {
            assertEquals(CampaignDto.class, result.getClass());
            verify(campaignMapper).dtoToCampaign(input);
            verify(campaignMapper).campaignToDto(any(Campaign.class));
            verify(campaignRepository).save(any());
        });
    }

    @Test
    public void deleteCampaign_ShouldCallDeleteByIdMethod() {
        Long id = 1L;

        campaignService.deleteCampaign(id);

        assertAll(() -> {
            verify(campaignRepository).deleteById(id);
        });
    }

    @Test
    public void updateCampaignData_shouldReturnCampaignDto() throws CampaignNotExistsException {
        CampaignDto input = new CampaignDto();

        Campaign fetchedCampaign = new Campaign();
        Image campaignImage = new Image();
        campaignImage.setId(1L);
        fetchedCampaign.setCampaignImage(campaignImage);

        when(campaignRepository.findById(any())).thenReturn(Optional.of(fetchedCampaign));
        when(campaignMapper.dtoToCampaign(any(CampaignDto.class))).thenReturn(new Campaign());
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(new CampaignDto());
        when(campaignRepository.save(any())).thenReturn(new Campaign());

        campaignService.updateCampaignData(input);

        assertAll(() -> {
            verify(campaignRepository).findById(any());
            verify(campaignMapper).dtoToCampaign(any(CampaignDto.class));
            verify(campaignMapper).campaignToDto(any(Campaign.class));
            verify(campaignRepository).save(any());
        });
    }

    @Test
    public void updateCampaignData_shouldThrowException_CampaignDtoWithoutValidIdProvided() {
        CampaignDto input = new CampaignDto();

        when(campaignRepository.findById(any())).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(CampaignNotExistsException.class, () -> campaignService.updateCampaignData(input));
            verify(campaignRepository).findById(any());
        });
    }

}