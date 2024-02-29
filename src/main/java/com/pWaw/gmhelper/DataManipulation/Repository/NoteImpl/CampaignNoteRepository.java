package com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CampaignNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignNoteRepository extends JpaRepository<CampaignNote, Long> {
}
