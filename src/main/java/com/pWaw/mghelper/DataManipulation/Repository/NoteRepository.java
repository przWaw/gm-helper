package com.pWaw.mghelper.DataManipulation.Repository;

import com.pWaw.mghelper.DataManipulation.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
