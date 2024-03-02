package com.pWaw.gmhelper.DataManipulation.Mappers;

import com.pWaw.gmhelper.DataManipulation.DTO.CampaignDto;
import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampaignMapper {
    @Mapping(target = "campaignImage.id", source = "imageId")
    Campaign dtoToCampaign(CampaignDto campaignDto);
    @Mapping(target = "imageId", source = "campaignImage.id")
    CampaignDto campaignToDto(Campaign campaign);
    List<Campaign> dtoToCampaign(List<CampaignDto> campaignsDtos);
    List<CampaignDto> campaignToDto(List<Campaign> campaigns);
}
