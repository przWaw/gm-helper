package com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CampaignNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignNoteRepository extends JpaRepository<CampaignNote, Long> {
    List<CampaignNote> findAllByCampaign_Id(Long campaignId);

}
