package com.pWaw.mghelper.DataManipulation.Mappers;

import com.pWaw.mghelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.mghelper.DataManipulation.Model.Campaign;
import org.mapstruct.Mapper;

@Mapper
public interface CampaignMapper {
    Campaign dtoToCampaign(CampaignDto campaignDto);
    CampaignDto campaignToDto(Campaign campaign);
}
