package com.pWaw.gmhelper.DataManipulation.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Simple object to carry needed information for campaign objects on frontend")
public class CampaignDto {
    private Long id;
    private String campaignName;
    private Long imageId;
}
