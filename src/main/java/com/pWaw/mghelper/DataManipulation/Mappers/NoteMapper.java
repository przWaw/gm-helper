package com.pWaw.mghelper.DataManipulation.Mappers;

import com.pWaw.mghelper.DataManipulation.DTO.CampaignNoteDto;
import com.pWaw.mghelper.DataManipulation.DTO.CharacterNoteDto;
import com.pWaw.mghelper.DataManipulation.DTO.NoteDto;
import com.pWaw.mghelper.DataManipulation.Model.Note;
import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CampaignNote;
import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CharacterNote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Mapping(source = "characterId", target = "character.id")
    CharacterNote dtoToCharacterNote(CharacterNoteDto noteDto);
    @Mapping(source = "character.id", target = "characterId")
    CharacterNoteDto characterNoteToDto(CharacterNote characterNote);
    @Mapping(source = "campaignId", target = "campaign.id")
    CampaignNote dtoToCampaignNote(CampaignNoteDto noteDto);
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignNoteDto campaignNoteToDto(CampaignNote campaignNote);
    Note dtoToNote(NoteDto noteDto);
    NoteDto noteToDto(Note note);
    List<CharacterNote> dtoToCharacterNote(List<CharacterNoteDto> noteDto);
    List<CharacterNoteDto> characterNoteToDto(List<CharacterNote> characterNote);
    List<CampaignNote> dtoToCampaignNote(List<CampaignNoteDto> noteDto);
    List<CampaignNoteDto> campaignNoteToDto(List<CampaignNote> campaignNote);
    List<Note> dtoToNote(List<NoteDto> noteDto);
    List<NoteDto> noteToDto(List<Note> note);
}
