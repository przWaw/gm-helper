package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping(CharacterController.DataPath.ROOT)
public interface CharacterController {

    class DataPath {
        private DataPath() {}
        public static final String ROOT = "api/gm-helper/data/character/";
    }

    @GetMapping("{id}")
    ResponseEntity<CharacterDto> getCharacter(@PathVariable Long id) throws CharacterNotExistsException;

    @GetMapping("campaign/{id}")
    ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId);

    @GetMapping("all")
    ResponseEntity<List<CharacterDto>> getAllCharacters();

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<CharacterDto> addCharacter(@RequestPart CharacterDto characterDto,  @RequestPart(required = false) MultipartFile image) throws EmptyFileSendException;

    @PutMapping
    ResponseEntity<CharacterDto> updateCharacter(@RequestBody CharacterDto characterDto) throws CharacterNotExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCharacter(@PathVariable Long id);
}
