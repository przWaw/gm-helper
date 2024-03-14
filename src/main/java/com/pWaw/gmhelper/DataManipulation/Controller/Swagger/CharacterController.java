package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CharacterController.DataPath.ROOT)
@Tag(name = "Character Controller", description = "Class for character data manipulation.")
public interface CharacterController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/characters/";
    }

    @Operation(summary = "Get character", description = "Get character with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Character with given id was not found in database")
    })
    @GetMapping("{id}")
    ResponseEntity<CharacterDto> getCharacter(@PathVariable Long id) throws CharacterNotExistsException;

    @Operation(summary = "Get characters from campaign", description = "Get character associated with campaign specified by campaign id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characters successfully retrieved"),
    })
    @GetMapping("campaign/{id}")
    ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId);

    @Operation(summary = "Get all characters", description = "Get list of all present characters in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all characters successfully retrieved"),
    })
    @GetMapping
    ResponseEntity<List<CharacterDto>> getAllCharacters();

    @Operation(summary = "Create character", description = "Post with body describing character to create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created")
    })
    @PostMapping
    ResponseEntity<CharacterDto> addCharacter(@RequestBody CharacterDto characterDto);

    @Operation(summary = "Update character", description = "Put with body containing updated form of character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character successfully updated"),
            @ApiResponse(responseCode = "404", description = "Character with given id was not found in database")
    })
    @PutMapping
    ResponseEntity<CharacterDto> updateCharacter(@RequestBody CharacterDto characterDto) throws CharacterNotExistsException;

    @Operation(summary = "Delete character", description = "Delete character with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character was successfully deleted")
    })
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCharacter(@PathVariable Long id);
}
