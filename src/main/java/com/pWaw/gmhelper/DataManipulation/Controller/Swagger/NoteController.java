package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.NoteNotExistsException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(NoteController.DataPath.ROOT)
@Tag(name = "Note Controller", description = "Class for note data manipulation")
public interface NoteController {

    class DataPath{
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/note/";
    }

    @GetMapping("{id}/campaign")
    ResponseEntity<List<CampaignNoteDto>> getNotesForCampaign(@PathVariable Long id);

    @GetMapping("{id}/character")
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
