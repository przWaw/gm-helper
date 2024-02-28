package com.pWaw.mghelper.DataManipulation.Mappers;

import com.pWaw.mghelper.DataManipulation.DTO.CharacterDto;
import com.pWaw.mghelper.DataManipulation.Model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CharacterMapper {

    @Mapping(target = "campaign.id", source = "campaignId")
    Character dtoToCharacter(CharacterDto characterDto);

    @Mapping(target = "campaignId", source = "campaign.id")
    CharacterDto characterToDto(Character character);
}
