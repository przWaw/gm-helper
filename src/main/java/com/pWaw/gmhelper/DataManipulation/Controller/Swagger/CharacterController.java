package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CharacterController.DataPath.ROOT)
public interface CharacterController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/character/";
    }

    @GetMapping
    ResponseEntity<CharacterDto> getCharacter(Long id);

    @GetMapping("campaign/{id}")
    ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId);

    @GetMapping("all")
    ResponseEntity<List<CharacterDto>> getAllCharacters();

    @PostMapping
    ResponseEntity<CharacterDto> addCharacter(@RequestBody CharacterDto characterDto);

    @PutMapping
    ResponseEntity<CharacterDto> updateCharacter(@RequestBody CharacterDto characterDto);

    @DeleteMapping
    ResponseEntity<Void> deleteCharacter(Long id);
}
