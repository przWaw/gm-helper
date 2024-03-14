package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
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

    @GetMapping("{id}")
    ResponseEntity<CharacterDto> getCharacter(@PathVariable Long id) throws CharacterNotExistsException;

    @GetMapping("campaign/{id}")
    ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId);

    @GetMapping
    ResponseEntity<List<CharacterDto>> getAllCharacters();

    @PostMapping
    ResponseEntity<CharacterDto> addCharacter(@RequestBody CharacterDto characterDto);

    @PutMapping
    ResponseEntity<CharacterDto> updateCharacter(@RequestBody CharacterDto characterDto) throws CharacterNotExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCharacter(@PathVariable Long id);
}
