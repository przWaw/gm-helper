package com.pWaw.mghelper.DataManipulation.Mappers;

import com.pWaw.mghelper.DataManipulation.DTO.CampaignNoteDto;
import com.pWaw.mghelper.DataManipulation.DTO.CharacterNoteDto;
import com.pWaw.mghelper.DataManipulation.DTO.NoteDto;
import com.pWaw.mghelper.DataManipulation.Model.Note;
import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CampaignNote;
import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CharacterNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NoteMapper {
    @Mapping(source = "characterId", target = "character.id")
    CharacterNote dtoToCharacterNote(CharacterNoteDto noteDto);
    @Mapping(source = "campaignId", target = "campaign.id")
    CampaignNote dtoToCampaignNote(CampaignNoteDto noteDto);
    @Mapping(source = "character.id", target = "characterId")
    CharacterNoteDto characterNoteToDto(CharacterNote characterNote);
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignNoteDto campaignNoteToDto(CampaignNote campaignNote);
    NoteDto noteToDto(Note note);
    Note dtoToNote(NoteDto noteDto);
}
