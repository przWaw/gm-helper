package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.NoteNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.NoteMapper;
import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CampaignNote;
import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CharacterNote;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CampaignNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CharacterNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final CampaignNoteRepository campaignNoteRepository;
    private final CharacterNoteRepository characterNoteRepository;
    private final NoteMapper noteMapper;

    public List<CampaignNoteDto> getAllNotesForCampaign(Long campaignId) {
        List<CampaignNote> campaignNotes = campaignNoteRepository.findAllByCampaign_Id(campaignId);
        return noteMapper.campaignNoteToDto(campaignNotes);
    }

    public List<CharacterNoteDto> getAllNotesForCharacter(Long characterId) {
        List<CharacterNote> characterNotes = characterNoteRepository.findAllByCharacter_Id(characterId);
        return noteMapper.characterNoteToDto(characterNotes);
    }

    public CampaignNoteDto createCampaignNote(CampaignNoteDto noteDto) {
        CampaignNote noteToSave = noteMapper.dtoToCampaignNote(noteDto);
        return noteMapper.campaignNoteToDto(campaignNoteRepository.save(noteToSave));
    }

    public CharacterNoteDto createCharacterNote(CharacterNoteDto noteDto) {
        CharacterNote noteToSave = noteMapper.dtoToCharacterNote(noteDto);
        return noteMapper.characterNoteToDto(characterNoteRepository.save(noteToSave));
    }

    public CampaignNoteDto updateCampaignNote(CampaignNoteDto noteDto) throws NoteNotExistsException {
        if (!noteRepository.existsById(noteDto.getId())) {
            throw new NoteNotExistsException("Wrong id was provided during update operation, note does not exists");
        }
        CampaignNote campaignNote = noteMapper.dtoToCampaignNote(noteDto);
        return noteMapper.campaignNoteToDto(campaignNoteRepository.save(campaignNote));
    }

    public CharacterNoteDto updateCharacterNote(CharacterNoteDto noteDto) throws NoteNotExistsException {
        if (!noteRepository.existsById(noteDto.getId())) {
            throw new NoteNotExistsException("Wrong id was provided during update operation, note does not exists");
        }
        CharacterNote characterNote = noteMapper.dtoToCharacterNote(noteDto);
        return noteMapper.characterNoteToDto(characterNoteRepository.save(characterNote));
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
