package com.pWaw.mghelper.DataManipulation.DTO;

import lombok.Data;

@Data
public class CampaignDto {
    private Long id;
    private String campaignName;
    private byte[] campaignImage;
    private String fileType;
}
