package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDetails;
import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageServiceTest {

    @Mock
    private static ImageRepository imageRepository;

    @Mock
    private static ImageMapper imageMapper;

    private static ImageService imageService;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        imageService = new ImageService(imageRepository, imageMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        validateMockitoUsage();
        openMocks.close();
    }

    @Test
    public void uploadImage_shouldReturnImageDetails() throws EmptyFileSendException {
        MultipartFile file = new MockMultipartFile("image.png", new byte[0]);

        when(imageMapper.dtoToImage(any(ImageDto.class))).thenReturn(new Image());
        when(imageMapper.imageToDetails(any(Image.class))).thenReturn(new ImageDetails());
        when(imageRepository.save(any())).thenReturn(new Image());

        ImageDetails result = imageService.uploadImage(file);

        assertAll(() -> {
            assertEquals(result.getClass(), ImageDetails.class);
            verify(imageMapper, times(1)).imageToDetails(any(Image.class));
            verify(imageMapper, times(1)).dtoToImage(any(ImageDto.class));
            verify(imageRepository, times(1)).save(any());
        });
    }

    @Test
    public void uploadImage_shouldThrowException() throws IOException {
        MultipartFile file = mock(MultipartFile.class);

        when(file.getBytes()).thenThrow(IOException.class);

        assertAll(() -> {
            assertThrows(EmptyFileSendException.class, () -> imageService.uploadImage(file));
        });
    }
}