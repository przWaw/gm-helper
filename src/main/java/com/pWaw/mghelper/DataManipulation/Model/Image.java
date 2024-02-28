package com.pWaw.mghelper.DataManipulation.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Data;

@Embeddable
@Data
public class Image {

    private String fileType;

    private String name;

    @Lob
    private byte[] imageData;
}
