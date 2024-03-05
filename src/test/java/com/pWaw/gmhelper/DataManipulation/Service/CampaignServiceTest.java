package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CampaignServiceTest {

    @Mock
    private static CampaignRepository campaignRepository;
    @Mock
    private static CampaignMapper campaignMapper;
    @Mock
    private static ImageRepository imageRepository;
    @Mock
    private static ImageMapper imageMapper;
    private static  CampaignService campaignService;
    private AutoCloseable openMocks;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        campaignService = new CampaignService(campaignRepository, campaignMapper, imageRepository, imageMapper);
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



}