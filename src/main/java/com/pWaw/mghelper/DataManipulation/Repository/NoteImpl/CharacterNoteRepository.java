package com.pWaw.mghelper.DataManipulation.Repository.NoteImpl;

import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CharacterNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterNoteRepository extends JpaRepository<CharacterNote, Long> {
}
