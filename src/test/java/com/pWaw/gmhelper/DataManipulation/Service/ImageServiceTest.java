package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.validateMockitoUsage;

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
}