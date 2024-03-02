package com.pWaw.gmhelper.DataManipulation.Mappers;

import com.pWaw.gmhelper.DataManipulation.DTO.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(target = "campaign.id", source = "campaignId")
    @Mapping(target = "characterPortrait.id", source = "portraitId")
    Character dtoToCharacter(CharacterDto characterDto);

    @Mapping(target = "campaignId", source = "campaign.id")
    @Mapping(target = "portraitId", source = "characterPortrait.id")
    CharacterDto characterToDto(Character character);

    List<Character> dtoToCharacter(List<CharacterDto> characterDtos);
    List<CharacterDto> characterToDto(List<Character> characters);
}
