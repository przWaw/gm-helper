package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.Mappers.NoteMapper;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CampaignNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl.CharacterNoteRepository;
import com.pWaw.gmhelper.DataManipulation.Repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.validateMockitoUsage;

class NoteServiceTest {

    @Mock
    private static NoteRepository noteRepository;

    @Mock
    private static CampaignNoteRepository campaignNoteRepository;

    @Mock
    private static CharacterNoteRepository characterNoteRepository;

    @Mock
    private static NoteMapper noteMapper;

    private static NoteService noteService;
    private AutoCloseable openMocks;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        noteService = new NoteService(noteRepository, campaignNoteRepository, characterNoteRepository, noteMapper);
    }

    @AfterEach
    public void tearDown() throws Exception {
        validateMockitoUsage();
        openMocks.close();
    }


}