package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.CharacterController;
import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService characterService;

    @Override
    public ResponseEntity<CharacterDto> getCharacter(Long id) throws CharacterNotExistsException {
        return ResponseEntity.ok().body(characterService.getCharacterById(id));
    }

    @Override
    public ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId) {
        return ResponseEntity.ok().body(characterService.getAllCharactersFromCampaign(campaignId));
    }

    @Override
    public ResponseEntity<List<CharacterDto>> getAllCharacters() {
        return ResponseEntity.ok().body(characterService.getAllCharacters());
    }

    @Override
    public ResponseEntity<CharacterDto> addCharacter(CharacterDto characterDto) {
        return new ResponseEntity<>(characterService.createCharacter(characterDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CharacterDto> updateCharacter(CharacterDto characterDto) throws CharacterNotExistsException {
        return ResponseEntity.ok().body(characterService.updateCharacter(characterDto));
    }

    @Override
    public ResponseEntity<Void> deleteCharacter(Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}
