package com.pWaw.gmhelper.DataManipulation.DTO;

import com.pWaw.gmhelper.DataManipulation.Model.Image;
import lombok.Data;

@Data
public class CampaignDto {
    private Long id;
    private String campaignName;
    private Image campaignImage;
}
