package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.CharacterController;
import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharacterControllerImpl implements CharacterController {

    private final CharacterService characterService;

    @Override
    public ResponseEntity<CharacterDto> getCharacter(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CharacterDto>> getAllCharactersFromCampaign(Long campaignId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CharacterDto>> getAllCharacters() {
        return null;
    }

    @Override
    public ResponseEntity<CharacterDto> addCharacter(CharacterDto characterDto) {
        return null;
    }

    @Override
    public ResponseEntity<CharacterDto> updateCharacter(CharacterDto characterDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteCharacter(Long id) {
        return null;
    }
}
