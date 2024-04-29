package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Note.CampaignNoteDto;
import com.pWaw.gmhelper.DataManipulation.DTO.Note.CharacterNoteDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CampaignNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.CharacterNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.NoteNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.NoteMapper;
import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CampaignNote;
import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CharacterNote;
import com.pWaw.gmhelper.DataManipulation.Repository.CampaignRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.CharacterRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CampaignNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CharacterNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Mock
    private static NoteRepository noteRepository;

    @Mock
    private static CampaignNoteRepository campaignNoteRepository;

    @Mock
    private static CharacterNoteRepository characterNoteRepository;

    @Mock
    private static NoteMapper noteMapper;

    @Mock
    private static CampaignRepository campaignRepository;

    @Mock static CharacterRepository characterRepository;

    private static NoteService noteService;
    private AutoCloseable openMocks;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        noteService = new NoteService(noteRepository, campaignNoteRepository, characterNoteRepository, noteMapper, campaignRepository, characterRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        validateMockitoUsage();
        openMocks.close();
    }

    @Test
    public void getCampaignNote_shouldReturnCampaignNoteDto() throws NoteNotExistsException {
        when(campaignNoteRepository.findById(any())).thenReturn(Optional.of(new CampaignNote()));
        when(noteMapper.campaignNoteToDto(any(CampaignNote.class))).thenReturn(new CampaignNoteDto());

        CampaignNoteDto result = noteService.getCampaignNoteDto(1L);

        assertAll(() -> {
            assertEquals(result.getClass(), CampaignNoteDto.class);
            verify(campaignNoteRepository, times(1)).findById(any());
            verify(noteMapper, times(1)).campaignNoteToDto(any(CampaignNote.class));
        });
    }

    @Test
    public void getCampaignNote_shouldThrowException() throws NoteNotExistsException {
        when(campaignNoteRepository.findById(any())).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(NoteNotExistsException.class, () ->  noteService.getCampaignNoteDto(1L));
            verify(campaignNoteRepository, times(1)).findById(any());
        });
    }

    @Test
    public void getCharacterNote_shouldReturnCharacterNoteDto() throws NoteNotExistsException {
        when(characterNoteRepository.findById(any())).thenReturn(Optional.of(new CharacterNote()));
        when(noteMapper.characterNoteToDto(any(CharacterNote.class))).thenReturn(new CharacterNoteDto());

        CharacterNoteDto result = noteService.getCharacterNoteDto(1L);

        assertAll(() -> {
            assertEquals(result.getClass(), CharacterNoteDto.class);
            verify(characterNoteRepository, times(1)).findById(any());
        });
    }

    @Test
    public void getCharacterNote_shouldThrowException() throws NoteNotExistsException {
        when(characterNoteRepository.findById(any())).thenReturn(Optional.empty());

        assertAll(() -> {
            assertThrows(NoteNotExistsException.class, () ->  noteService.getCharacterNoteDto(1L));
            verify(characterNoteRepository, times(1)).findById(any());
        });
    }

    @Test
    public void getAllNotesForCampaign_shouldReturnPage() {
        when(campaignNoteRepository.findAllByCampaign_Id(any(), any())).thenReturn(Page.empty());

        Page<CampaignNoteDto> result = noteService.getAllNotesForCampaign(1L, 1, 1);

        assertAll(() -> {
            verify(campaignNoteRepository, times(1)).findAllByCampaign_Id(any(), any());
        });
    }

    @Test
    public void getAllNotesForCharacter_shouldReturnPage() {
        when(characterNoteRepository.findAllByCharacter_Id(any(), any())).thenReturn(Page.empty());

        Page<CharacterNoteDto> result = noteService.getAllNotesForCharacter(1L, 1, 1);

        assertAll(() -> {
            verify(characterNoteRepository, times(1)).findAllByCharacter_Id(any(), any());
        });
    }

    @Test
    public void createCampaignNote_shouldReturnCampaignNoteDto() throws CampaignNotExistsException {

        when(noteMapper.campaignNoteToDto(any(CampaignNote.class))).thenReturn(new CampaignNoteDto());
        when(noteMapper.dtoToCampaignNote(any(CampaignNoteDto.class))).thenReturn(new CampaignNote());
        when(campaignNoteRepository.save(any())).thenReturn(new CampaignNote());
        when(campaignRepository.existsById(any())).thenReturn(true);

        CampaignNoteDto result = noteService.createCampaignNote(new CampaignNoteDto());

        assertAll(() -> {
            assertEquals(CampaignNoteDto.class, result.getClass());
            verify(noteMapper, times(1)).dtoToCampaignNote(any(CampaignNoteDto.class));
            verify(noteMapper, times(1)).campaignNoteToDto(any(CampaignNote.class));
            verify(campaignNoteRepository, times(1)).save(any());
        });

    }

    @Test
    public void createCampaignNote_shouldThrowException() {

        when(campaignRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(CampaignNotExistsException.class, () -> noteService.createCampaignNote(new CampaignNoteDto()));
            verify(campaignRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void createCharacterNote_shouldReturnCharacterNoteDto() throws CharacterNotExistsException {

        when(noteMapper.characterNoteToDto(any(CharacterNote.class))).thenReturn(new CharacterNoteDto());
        when(noteMapper.dtoToCharacterNote(any(CharacterNoteDto.class))).thenReturn(new CharacterNote());
        when(characterNoteRepository.save(any())).thenReturn(new CharacterNote());
        when(characterRepository.existsById(any())).thenReturn(true);

        CharacterNoteDto result = noteService.createCharacterNote(new CharacterNoteDto());

        assertAll(() -> {
            assertEquals(CharacterNoteDto.class, result.getClass());
            verify(noteMapper, times(1)).dtoToCharacterNote(any(CharacterNoteDto.class));
            verify(noteMapper, times(1)).characterNoteToDto(any(CharacterNote.class));
            verify(characterNoteRepository, times(1)).save(any());
        });

    }

    @Test
    public void createCharacterNote_ShouldThrowException() {

        when(characterRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(CharacterNotExistsException.class, () -> noteService.createCharacterNote(new CharacterNoteDto()));
            verify(characterRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void updateCampaignNote_shouldReturnCampaignNoteDto() throws CampaignNotExistsException, NoteNotExistsException {

        when(noteMapper.campaignNoteToDto(any(CampaignNote.class))).thenReturn(new CampaignNoteDto());
        when(noteMapper.dtoToCampaignNote(any(CampaignNoteDto.class))).thenReturn(new CampaignNote());
        when(campaignNoteRepository.save(any())).thenReturn(new CampaignNote());
        when(campaignRepository.existsById(any())).thenReturn(true);
        when(campaignNoteRepository.existsById(any())).thenReturn(true);

        CampaignNoteDto result = noteService.updateCampaignNote(new CampaignNoteDto());

        assertAll(() -> {
            assertEquals(CampaignNoteDto.class, result.getClass());
            verify(noteMapper, times(1)).dtoToCampaignNote(any(CampaignNoteDto.class));
            verify(noteMapper, times(1)).campaignNoteToDto(any(CampaignNote.class));
            verify(campaignRepository, times(1)).existsById(any());
            verify(campaignNoteRepository, times(1)).existsById(any());
            verify(campaignNoteRepository, times(1)).save(any());
        });

    }

    @Test
    public void updateCampaignNote_shouldThrowNoteNoteNotExistsException() {

        when(campaignNoteRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(NoteNotExistsException.class, () -> noteService.updateCampaignNote(new CampaignNoteDto()));
            verify(campaignNoteRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void updateCampaignNote_shouldThrowCampaignNotExistsException() {

        when(campaignNoteRepository.existsById(any())).thenReturn(true);
        when(campaignRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(CampaignNotExistsException.class, () -> noteService.updateCampaignNote(new CampaignNoteDto()));
            verify(campaignNoteRepository, times(1)).existsById(any());
            verify(campaignRepository, times(1)).existsById(any());
        });
    }


    @Test
    public void updateCharacterNote_shouldReturnCharacterNoteDto() throws NoteNotExistsException, CharacterNotExistsException {

        when(noteMapper.characterNoteToDto(any(CharacterNote.class))).thenReturn(new CharacterNoteDto());
        when(noteMapper.dtoToCharacterNote(any(CharacterNoteDto.class))).thenReturn(new CharacterNote());
        when(characterNoteRepository.save(any())).thenReturn(new CharacterNote());
        when(characterRepository.existsById(any())).thenReturn(true);
        when(characterNoteRepository.existsById(any())).thenReturn(true);

        CharacterNoteDto result = noteService.updateCharacterNote(new CharacterNoteDto());

        assertAll(() -> {
            assertEquals(CharacterNoteDto.class, result.getClass());
            verify(noteMapper, times(1)).dtoToCharacterNote(any(CharacterNoteDto.class));
            verify(noteMapper, times(1)).characterNoteToDto(any(CharacterNote.class));
            verify(characterRepository, times(1)).existsById(any());
            verify(characterNoteRepository, times(1)).existsById(any());
            verify(characterNoteRepository, times(1)).save(any());
        });

    }

    @Test
    public void updateCharacterNote_shouldThrowNoteNoteNotExistsException() {

        when(characterNoteRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(NoteNotExistsException.class, () -> noteService.updateCharacterNote(new CharacterNoteDto()));
            verify(characterNoteRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void updateCharacterNote_shouldThrowCharacterNotExistsException() {

        when(characterNoteRepository.existsById(any())).thenReturn(true);
        when(characterRepository.existsById(any())).thenReturn(false);

        assertAll(() -> {
            assertThrows(CharacterNotExistsException.class, () -> noteService.updateCharacterNote(new CharacterNoteDto()));
            verify(characterNoteRepository, times(1)).existsById(any());
            verify(characterRepository, times(1)).existsById(any());
        });
    }

    @Test
    public void deleteNote() {

        noteService.deleteNote(1L);

        assertAll(() -> {
            verify(noteRepository, times(1)).deleteById(any());
        });
    }

}