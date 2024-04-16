package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.NoteController;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.NoteNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;

    @Override
    public ResponseEntity<Page<CampaignNoteDto>> getNotesForCampaign(Long id, Integer page, Integer pageSize) {
        return ResponseEntity.ok().body(noteService.getAllNotesForCampaign(id, page, pageSize));
    }

    @Override
    public ResponseEntity<Page<CharacterNoteDto>> getNotesForCharacter(Long id, Integer page, Integer pageSize) {
        return ResponseEntity.ok().body(noteService.getAllNotesForCharacter(id, page, pageSize));
    }

    @Override
    public ResponseEntity<CampaignNoteDto> createCampaignNote(CampaignNoteDto noteDto) {
        CampaignNoteDto dto = noteService.createCampaignNote(noteDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DataPath.ROOT + "/campaign-notes/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @Override
    public ResponseEntity<CharacterNoteDto> createCharacterNote(CharacterNoteDto noteDto) {
        CharacterNoteDto dto = noteService.createCharacterNote(noteDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DataPath.ROOT + "/character-notes/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @Override
    public ResponseEntity<CampaignNoteDto> updateCampaignNote(CampaignNoteDto noteDto) throws NoteNotExistsException {
        return ResponseEntity.ok().body(noteService.updateCampaignNote(noteDto));
    }

    @Override
    public ResponseEntity<CharacterNoteDto> updateCharacterNote(CharacterNoteDto noteDto) throws NoteNotExistsException {
        return ResponseEntity.ok().body(noteService.updateCharacterNote(noteDto));
    }

    @Override
    public ResponseEntity<Void> deleteNote(Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CharacterNoteDto> getNoteForCharacter(Long id) throws NoteNotExistsException {
        return ResponseEntity.ok().body(noteService.getCharacterNoteDto(id));
    }

    @Override
    public ResponseEntity<CampaignNoteDto> getNoteForCampaign(Long id) throws NoteNotExistsException {
        return ResponseEntity.ok().body(noteService.getCampaignNoteDto(id));
    }
}
