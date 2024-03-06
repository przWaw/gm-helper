package com.pWaw.gmhelper.DataManipulation.DTO.Campaign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Simple object to carry needed information for campaign objects on frontend")
public class CampaignDto {
    private Long id;
    private String campaignName;
    private Long imageId;
}
