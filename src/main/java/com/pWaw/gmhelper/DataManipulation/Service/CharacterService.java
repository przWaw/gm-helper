package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CharacterMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Character;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.CharacterRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final ImageRepository imageRepository;
    private final CampaignRepository campaignRepository;
    private final CharacterMapper characterMapper;

    public CharacterDto getCharacterById(Long id) throws CharacterNotExistsException {
        Optional<Character> character = characterRepository.findById(id);
        if(character.isEmpty()) {
            throw new CharacterNotExistsException("Wrong id was provided during get operation, character does not exists");
        }
        return characterMapper.characterToDto(character.get());
    }

    public Page<CharacterDto> getAllCharactersFromCampaign(Long campaignId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return characterRepository.findAllByCampaign_Id(campaignId, pageable).map(characterMapper::characterToDto);
    }

    public Page<CharacterDto> getAllCharacters(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return characterRepository.findAll(pageable).map(characterMapper::characterToDto);

    }

    public CharacterDto createCharacter(CharacterDto characterDto) {
        Character character = characterMapper.dtoToCharacter(characterDto);
        if (!campaignRepository.existsById(characterDto.getCampaignId())) {
            character.setCampaign(null);
        }
        if (!imageRepository.existsById(characterDto.getPortraitId())) {
            character.setCharacterPortrait(null);
        }
        return characterMapper.characterToDto(characterRepository.save(character));
    }

    public CharacterDto updateCharacter(CharacterDto characterDto) throws CharacterNotExistsException {
        Optional<Character> character = characterRepository.findById(characterDto.getId());
        if(character.isEmpty()) {
            throw new CharacterNotExistsException("Wrong id was provided during update operation, character does not exists");
        }
        Character characterToUpdate = characterMapper.dtoToCharacter(characterDto);
        if (!imageRepository.existsById(characterDto.getPortraitId())) {
            characterToUpdate.setCharacterPortrait(null);
        }
        if (!campaignRepository.existsById(characterDto.getCampaignId())) {
            characterToUpdate.setCampaign(null);
        }
        return characterMapper.characterToDto(characterRepository.save(characterToUpdate));
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

}