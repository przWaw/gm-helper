package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.CharacterController;
import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService characterService;

    @Override
    public ResponseEntity<CharacterDto> getCharacter(Long id) throws CharacterNotExistsException {
        return ResponseEntity.ok().body(characterService.getCharacterById(id));
    }

    @Override
    public ResponseEntity<Page<CharacterDto>> getAllCharactersFromCampaign(Long campaignId, Integer page, Integer pageSize) {
        return ResponseEntity.ok().body(characterService.getAllCharactersFromCampaign(campaignId, page, pageSize));
    }

    @Override
    public ResponseEntity<Page<CharacterDto>> getAllCharacters(Integer page, Integer pageSize) {
        return ResponseEntity.ok().body(characterService.getAllCharacters(page, pageSize));
    }

    @Override
    public ResponseEntity<CharacterDto> addCharacter(CharacterDto characterDto) {
        CharacterDto dto = characterService.createCharacter(characterDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(DataPath.ROOT + "/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
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
