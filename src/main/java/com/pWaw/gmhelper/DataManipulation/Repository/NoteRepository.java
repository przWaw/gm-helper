package com.pWaw.gmhelper.DataManipulation.Repository;

import com.pWaw.gmhelper.DataManipulation.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
