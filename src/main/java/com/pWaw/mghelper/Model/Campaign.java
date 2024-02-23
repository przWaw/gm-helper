package com.pWaw.mghelper.Model;

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

}
