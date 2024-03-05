package com.pWaw.gmhelper.DataManipulation.DTO.Image;

import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Builder
public class ImageDto {
    private Long id;
    private String name;
    private String fileType;
    private Long size;
    private byte[] imageData;

    public static ImageDto readFromMultipart(MultipartFile image) throws EmptyFileSendException {
        try {
            return ImageDto.builder()
                    .name(image.getName())
                    .fileType(image.getContentType())
                    .size(image.getSize())
                    .imageData(image.getBytes())
                    .build();
        } catch (IOException e) {
            throw new EmptyFileSendException();
        }
    }
}
