package com.pWaw.mghelper.DataManipulation.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String campaignName;

    @Lob
    private byte[] campaignImage;

    private String fileType;

}
