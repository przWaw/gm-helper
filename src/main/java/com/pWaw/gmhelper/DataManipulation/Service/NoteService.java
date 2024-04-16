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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final CampaignNoteRepository campaignNoteRepository;
    private final CharacterNoteRepository characterNoteRepository;
    private final NoteMapper noteMapper;

    public CampaignNoteDto getCampaignNoteDto(Long id) throws NoteNotExistsException {
        Optional<CampaignNote> note = campaignNoteRepository.findById(id);
        if (note.isEmpty()) {
            throw new NoteNotExistsException();
        }
        return noteMapper.campaignNoteToDto(note.get());
    }

    public CharacterNoteDto getCharacterNoteDto(Long id) throws NoteNotExistsException {
        Optional<CharacterNote> note = characterNoteRepository.findById(id);
        if (note.isEmpty()) {
            throw new NoteNotExistsException();
        }
        return noteMapper.characterNoteToDto(note.get());
    }

    public Page<CampaignNoteDto> getAllNotesForCampaign(Long campaignId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return campaignNoteRepository.findAllByCampaign_Id(campaignId, pageable).map(noteMapper::campaignNoteToDto);
    }

    public Page<CharacterNoteDto> getAllNotesForCharacter(Long characterId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return characterNoteRepository.findAllByCharacter_Id(characterId, pageable).map(noteMapper::characterNoteToDto);
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
