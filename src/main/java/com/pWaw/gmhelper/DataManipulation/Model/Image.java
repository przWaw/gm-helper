package com.pWaw.gmhelper.DataManipulation.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private String fileType;

    private String name;

    @Lob
    private byte[] imageData;
}
