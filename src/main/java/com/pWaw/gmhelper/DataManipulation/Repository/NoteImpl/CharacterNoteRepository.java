package com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CharacterNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterNoteRepository extends JpaRepository<CharacterNote, Long> {
    Page<CharacterNote> findAllByCharacter_Id(Long characterId, Pageable pageable);
}
