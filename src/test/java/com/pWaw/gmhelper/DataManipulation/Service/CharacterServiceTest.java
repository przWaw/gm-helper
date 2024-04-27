package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Character.CharacterDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.CharacterMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Character;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.CharacterRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CharacterServiceTest {

    @Mock
    private static CharacterRepository characterRepository;
    @Mock
    private static CharacterMapper characterMapper;

    @Mock
    private static CampaignRepository campaignRepository;

    @Mock
    private static ImageRepository imageRepository;

    private static CharacterService characterService;

    private AutoCloseable openMocks;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        characterService = new CharacterService(characterRepository, imageRepository, campaignRepository, characterMapper);
    }

    @AfterEach
    public void tearDown() throws Exception {
        validateMockitoUsage();
        openMocks.close();
    }

    @Test
    public void getCharacter_shouldReturnCharacter_idProvided() throws CharacterNotExistsException {
        Long id = 1L;

        when(characterRepository.findById(id)).thenReturn(Optional.of(new Character()));
        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());

        CharacterDto result = characterService.getCharacterById(id);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result.getClass(), CharacterDto.class);
            verify(characterRepository, times(1)).findById(any());
            verify(characterMapper, times(1)).characterToDto(any(Character.class));
        });
    }

    @Test
    public void getCharacter_shouldThrowException_wrongIdProvided() {
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(CharacterNotExistsException.class, () -> characterService.getCharacterById(1L));
            verify(characterRepository, times(1)).findById(any());
        });
    }

    @Test
    public void getCharactersFromCampaign_shouldReturnPageOfCharactersDto() {
        Pageable pageable = PageRequest.of(1, 2);

        when(characterRepository.findAllByCampaign_Id(any(), any())).thenReturn(Page.empty(pageable));

        Page<CharacterDto> result = characterService.getAllCharactersFromCampaign(1L, 1, 2);

        assertAll(() -> {
            assertEquals(0, result.getTotalElements());
            verify(characterRepository, times(1)).findAllByCampaign_Id(any(), any());
        });
    }

    @Test
    public void getAllCharacters_shouldReturnPage(){
        Pageable pageable = PageRequest.of(1, 2);

        when(characterRepository.findAll(pageable)).thenReturn(Page.empty(pageable));

        Page<CharacterDto> result = characterService.getAllCharacters(1, 2);

        assertAll(() -> {
            assertEquals(0, result.getTotalElements());
            verify(characterRepository, times(1)).findAll(pageable);
        });
    }

    @Test
    public void createCharacter_shouldReturnCharacterDto() {
        CharacterDto input = new CharacterDto();

        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());

        CharacterDto result = characterService.createCharacter(input);

        assertAll(() -> {
            assertEquals(CharacterDto.class, result.getClass());
            verify(characterMapper, times(1)).characterToDto(any(Character.class));
            verify(characterMapper,times(1)).dtoToCharacter(any(CharacterDto.class));
            verify(characterRepository, times(1)).save(any());
            verify(campaignRepository, times(1)).existsById(any());
            verify(imageRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void createCharacter_characterDtoWithImageAndCampaignProvided() {
        CharacterDto input = new CharacterDto();

        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(true);
        when(imageRepository.existsById(any())).thenReturn(true);

        CharacterDto result = characterService.createCharacter(input);

        assertAll(() -> {
            assertEquals(CharacterDto.class, result.getClass());
            verify(characterMapper, times(1)).characterToDto(any(Character.class));
            verify(characterMapper,times(1)).dtoToCharacter(any(CharacterDto.class));
            verify(characterRepository, times(1)).save(any());
            verify(campaignRepository, times(1)).existsById(any());
            verify(imageRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void createCharacter_characterDtoWithImageProvided() {
        CharacterDto input = new CharacterDto();

        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(false);
        when(imageRepository.existsById(any())).thenReturn(true);

        CharacterDto result = characterService.createCharacter(input);

        assertAll(() -> {
            assertEquals(CharacterDto.class, result.getClass());
            verify(characterMapper, times(1)).characterToDto(any(Character.class));
            verify(characterMapper,times(1)).dtoToCharacter(any(CharacterDto.class));
            verify(characterRepository, times(1)).save(any());
            verify(campaignRepository, times(1)).existsById(any());
            verify(imageRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void createCharacter_characterDtoWithCampaignProvided() {
        CharacterDto input = new CharacterDto();

        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(true);
        when(imageRepository.existsById(any())).thenReturn(false);

        CharacterDto result = characterService.createCharacter(input);

        assertAll(() -> {
            assertEquals(CharacterDto.class, result.getClass());
            verify(characterMapper, times(1)).characterToDto(any(Character.class));
            verify(characterMapper,times(1)).dtoToCharacter(any(CharacterDto.class));
            verify(characterRepository, times(1)).save(any());
            verify(campaignRepository, times(1)).existsById(any());
            verify(imageRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void updateCharacter_shouldReturnCharacterDto() throws CharacterNotExistsException {

        when(characterRepository.findById(any())).thenReturn(Optional.of(new Character()));
        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());

        characterService.updateCharacter(new CharacterDto());

        assertAll(() -> {
            verify(characterRepository).findById(any());
            verify(characterMapper).dtoToCharacter(any(CharacterDto.class));
            verify(characterMapper).characterToDto(any(Character.class));
            verify(characterRepository).save(any());
        });
    }

    @Test
    public void updateCharacter_characterDtoWithImageAndCampaignProvided() throws CharacterNotExistsException {

        when(characterRepository.findById(any())).thenReturn(Optional.of(new Character()));
        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(true);
        when(imageRepository.existsById(any())).thenReturn(true);

        characterService.updateCharacter(new CharacterDto());

        assertAll(() -> {
            verify(characterRepository).findById(any());
            verify(characterMapper).dtoToCharacter(any(CharacterDto.class));
            verify(characterMapper).characterToDto(any(Character.class));
            verify(characterRepository).save(any());
        });
    }

    @Test
    public void updateCharacter_characterDtoWithCampaignProvided() throws CharacterNotExistsException {

        when(characterRepository.findById(any())).thenReturn(Optional.of(new Character()));
        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(true);
        when(imageRepository.existsById(any())).thenReturn(false);

        characterService.updateCharacter(new CharacterDto());

        assertAll(() -> {
            verify(characterRepository).findById(any());
            verify(characterMapper).dtoToCharacter(any(CharacterDto.class));
            verify(characterMapper).characterToDto(any(Character.class));
            verify(characterRepository).save(any());
        });
    }

    @Test
    public void updateCharacter_characterDtoWithImageProvided() throws CharacterNotExistsException {

        when(characterRepository.findById(any())).thenReturn(Optional.of(new Character()));
        when(characterMapper.characterToDto(any(Character.class))).thenReturn(new CharacterDto());
        when(characterMapper.dtoToCharacter(any(CharacterDto.class))).thenReturn(new Character());
        when(characterRepository.save(any())).thenReturn(new Character());
        when(campaignRepository.existsById(any())).thenReturn(false);
        when(imageRepository.existsById(any())).thenReturn(true);

        characterService.updateCharacter(new CharacterDto());

        assertAll(() -> {
            verify(characterRepository).findById(any());
            verify(characterMapper).dtoToCharacter(any(CharacterDto.class));
            verify(characterMapper).characterToDto(any(Character.class));
            verify(characterRepository).save(any());
        });
    }

    @Test
    public void updateCharacter_shouldThrowException() {
        CharacterDto input = new CharacterDto();
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(CharacterNotExistsException.class, () -> characterService.updateCharacter(input));
            verify(characterRepository, times(1)).findById(any());
        });
    }

    @Test
    public void deleteCharacter() {

        characterService.deleteCharacter(1L);

        assertAll(() -> {
            verify(characterRepository, times(1)).deleteById(any());
        });
    }

}