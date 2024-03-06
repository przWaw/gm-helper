package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Campaign.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CampaignMapper;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    @Test
    public void getAllCampaigns_shouldReturnListOfCampaignsDto() {
        when(campaignRepository.findAll()).thenReturn(List.of(new Campaign(), new Campaign()));
        when(campaignMapper.campaignToDto(anyList())).thenReturn(List.of(new CampaignDto(), new CampaignDto()));

        List<CampaignDto> result = campaignService.getAllCampaigns();

        assertAll(() -> {
            assertEquals(2, result.size());
            verify(campaignRepository, times(1)).findAll();
            verify(campaignMapper, times(1)).campaignToDto(anyList());
        });
    }

    @Test
    public void createCampaign_shouldCreateCampaignWithImage_ImageProvided() throws EmptyFileSendException {
        CampaignDto input = new CampaignDto();
        MultipartFile image = new MockMultipartFile("filename", new byte[5]);

        Image expectedImage = new Image();

        Campaign expected = new Campaign();
        expected.setCampaignName("test");
        expected.setCampaignImage(expectedImage);

        CampaignDto expectedDto = new CampaignDto();

        when(campaignMapper.dtoToCampaign(input)).thenReturn(expected);
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(expectedDto);
        when(campaignRepository.save(any())).thenReturn(expected);
        when(imageMapper.dtoToImage(any(ImageDto.class))).thenReturn(new Image());
        when(imageRepository.save(any())).thenReturn(expectedImage);

        CampaignDto result = campaignService.createCampaign(input, image);

        assertAll(() -> {
            verify(campaignMapper).dtoToCampaign(input);
            verify(campaignMapper).campaignToDto(any(Campaign.class));
            verify(campaignRepository).save(any());
            verify(imageMapper).dtoToImage(any(ImageDto.class));
            verify(imageRepository).save(any());
        });
    }

    @Test
    public void createCampaign_shouldCreateCampaignWithoutImage_ImageNotProvided() throws EmptyFileSendException {
        CampaignDto input = new CampaignDto();
        MultipartFile image = new MockMultipartFile("null", new byte[0]);

        Image expectedImage = new Image();

        Campaign expected = new Campaign();
        expected.setCampaignName("test");
        expected.setCampaignImage(expectedImage);

        CampaignDto expectedDto = new CampaignDto();

        when(campaignMapper.dtoToCampaign(input)).thenReturn(expected);
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(expectedDto);
        when(campaignRepository.save(any())).thenReturn(expected);
        when(imageMapper.dtoToImage(any(ImageDto.class))).thenReturn(new Image());
        when(imageRepository.save(any())).thenReturn(expectedImage);

        CampaignDto result = campaignService.createCampaign(input, image);

        assertAll(() -> {
            verify(campaignMapper).dtoToCampaign(input);
            verify(campaignMapper).campaignToDto(any(Campaign.class));
            verify(campaignRepository).save(any());
            verify(imageMapper, times(0)).dtoToImage(any(ImageDto.class));
            verify(imageRepository, times(0)).save(any());
        });
    }

    @Test
    public void deleteImage_ShouldCallDeleteByIdMethod() {
        Long id = 1L;

        campaignService.deleteCampaign(id);

        assertAll(() -> {
            verify(campaignRepository).deleteById(id);
        });
    }

    @Test
    public void updateCampaignData_shouldReturnCampaignDto_CampaignDtoWithoutImageIdProvided() throws CampaignNotExistsException {
        CampaignDto input = new CampaignDto();

        Campaign fetchedCampaign = new Campaign();
        Image campaignImage = new Image();
        campaignImage.setId(1L);
        fetchedCampaign.setCampaignImage(campaignImage);

        when(campaignRepository.findById(any())).thenReturn(Optional.of(fetchedCampaign));
        when(imageRepository.findById(any())).thenReturn(Optional.of(new Image()));
        when(campaignMapper.dtoToCampaign(any(CampaignDto.class))).thenReturn(new Campaign());
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(new CampaignDto());
        when(campaignRepository.save(any())).thenReturn(new Campaign());

        campaignService.updateCampaignData(input);

        assertAll(() -> {
            verify(campaignRepository).findById(any());
            verify(imageRepository).findById(any());
            verify(campaignMapper).dtoToCampaign(any(CampaignDto.class));
            verify(campaignMapper).campaignToDto(any(Campaign.class));
            verify(campaignRepository).save(any());
        });
    }

    @Test
    public void updateCampaignData_shouldReturnCampaignDto_CampaignDtoWithImageIdProvided() throws CampaignNotExistsException {
        CampaignDto input = new CampaignDto();
        input.setImageId(1L);

        when(campaignRepository.findById(any())).thenReturn(Optional.of(new Campaign()));
        when(imageRepository.findById(any())).thenReturn(Optional.of(new Image()));
        when(campaignMapper.dtoToCampaign(any(CampaignDto.class))).thenReturn(new Campaign());
        when(campaignMapper.campaignToDto(any(Campaign.class))).thenReturn(new CampaignDto());
        when(campaignRepository.save(any())).thenReturn(new Campaign());

        campaignService.updateCampaignData(input);

        assertAll(() -> {
            verify(campaignRepository).findById(any());
            verify(imageRepository).findById(any());
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