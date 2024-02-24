package com.pWaw.mghelper.DataManipulation.Repository;

import com.pWaw.mghelper.DataManipulation.Model.NoteImpl.CampaignNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignNoteRepository extends JpaRepository<CampaignNote, Long> {
}
