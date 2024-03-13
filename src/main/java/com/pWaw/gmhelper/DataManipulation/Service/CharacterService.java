package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CharacterMapper;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Character;
import com.pWaw.gmhelper.DataManipulation.Repository.CharacterRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public CharacterDto getCharacterById(Long id) throws CharacterNotExistsException {
        Optional<Character> character = characterRepository.findById(id);
        if(character.isEmpty()) {
            throw new CharacterNotExistsException();
        }
        return characterMapper.characterToDto(character.get());
    }

    public List<CharacterDto> getAllCharactersFromCampaign(Long campaignId) {
        List<Character> characters = characterRepository.findAllByCampaign_Id(campaignId);
        return characterMapper.characterToDto(characters);
    }

    public List<CharacterDto> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characterMapper.characterToDto(characters);
    }

    public CharacterDto createCharacter(CharacterDto characterDto) {
        Character character = characterMapper.dtoToCharacter(characterDto);
        return characterMapper.characterToDto(characterRepository.save(character));
    }

    public CharacterDto updateCharacter(CharacterDto characterDto) throws CharacterNotExistsException {
        Optional<Character> character = characterRepository.findById(characterDto.getId());
        if(character.isEmpty()) {
            throw new CharacterNotExistsException();
        }

        Character characterToUpdate = characterMapper.dtoToCharacter(characterDto);
        return characterMapper.characterToDto(characterRepository.save(characterToUpdate));
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

}

// TODO: add method to add image if character have null image