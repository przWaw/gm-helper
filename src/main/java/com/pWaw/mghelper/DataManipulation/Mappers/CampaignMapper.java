package com.pWaw.mghelper.DataManipulation.Mappers;

import com.pWaw.mghelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.mghelper.DataManipulation.Model.Campaign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignMapper {
    Campaign dtoToCampaign(CampaignDto campaignDto);
    CampaignDto campaignToDto(Campaign campaign);
    List<Campaign> dtoToCampaign(List<CampaignDto> campaignsDtos);
    List<CampaignDto> campaignToDto(List<Campaign> campaigns);
}
