package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.NoteNotExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(NoteController.DataPath.ROOT)
@Tag(name = "Note Controller", description = "Class for note data manipulation")
public interface NoteController {

    class DataPath{
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/notes/";
    }

    @Operation(summary = "Get notes from campaign/character", description = "Get notes associated with campaign/character specified by campaign id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes successfully retrieved"),
    })
    @GetMapping(value = "campaign-notes/{id}")
    ResponseEntity<Page<CampaignNoteDto>> getNotesForCampaign(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize);

    @Operation(summary = "Get notes from character", description = "Get notes associated with character specified by campaign id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notes successfully retrieved"),
    })
    @GetMapping("character-notes/{id}")
    ResponseEntity<Page<CharacterNoteDto>> getNotesForCharacter(@PathVariable Long id, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize);

    @Operation(summary = "Create note for campaign", description = "Create note that is tied to campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note successfully created")
    })
    @PostMapping("campaign-notes")
    ResponseEntity<CampaignNoteDto> createCampaignNote(@RequestBody CampaignNoteDto noteDto);

    @Operation(summary = "Create note for character", description = "Create note that is tied to character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note successfully created")
    })
    @PostMapping("character-notes")
    ResponseEntity<CharacterNoteDto> createCharacterNote(@RequestBody CharacterNoteDto noteDto);

    @Operation(summary = "Update note tied to campaign", description = "Put with body containing updated form of note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully updated"),
            @ApiResponse(responseCode = "404", description = "Note with given id was not found in database")
    })
    @PutMapping("campaign-notes")
    ResponseEntity<CampaignNoteDto> updateCampaignNote(@RequestBody CampaignNoteDto noteDto) throws NoteNotExistsException;

    @Operation(summary = "Update note tied to character", description = "Put with body containing updated form of note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note successfully updated"),
            @ApiResponse(responseCode = "404", description = "Note with given id was not found in database")
    })
    @PutMapping("character-notes")
    ResponseEntity<CharacterNoteDto> updateCharacterNote(@RequestBody CharacterNoteDto noteDto) throws NoteNotExistsException;

    @Operation(summary = "Delete note", description = "Delete note with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note deleted successfully")
    })
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteNote(@PathVariable Long id);
}
