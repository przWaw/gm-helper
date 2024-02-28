package com.pWaw.mghelper.DataManipulation.DTO;

import com.pWaw.mghelper.DataManipulation.Model.Image;
import lombok.Data;

@Data
public class CampaignDto {
    private Long id;
    private String campaignName;
    private Image campaignImage;
}
