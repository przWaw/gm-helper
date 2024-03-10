package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.NoteNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(NoteController.DataPath.ROOT)
public interface NoteController {

    class DataPath{
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/note/";
    }

    @GetMapping("campaign/{id}")
    ResponseEntity<List<CampaignNoteDto>> getNotesForCampaign(@PathVariable Long id);

    @GetMapping("character/{id}")
    ResponseEntity<List<CharacterNoteDto>> getNotesForCharacter(@PathVariable Long id);

    @PostMapping("campaign")
    ResponseEntity<CampaignNoteDto> createCampaignNote(@RequestBody CampaignNoteDto noteDto);

    @PostMapping("character")
    ResponseEntity<CharacterNoteDto> createCharacterNote(@RequestBody CharacterNoteDto noteDto);

    @PutMapping("campaign")
    ResponseEntity<CampaignNoteDto> updateCampaignNote(@RequestBody CampaignNoteDto noteDto) throws NoteNotExistsException;

    @PutMapping("character")
    ResponseEntity<CharacterNoteDto> updateCharacterNote(@RequestBody CharacterNoteDto noteDto) throws NoteNotExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteNote(@PathVariable Long id);
}
