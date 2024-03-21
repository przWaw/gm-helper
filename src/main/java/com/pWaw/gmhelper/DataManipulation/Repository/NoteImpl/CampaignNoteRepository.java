package com.pWaw.gmhelper.DataManipulation.Repository.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.NoteImpl.CampaignNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignNoteRepository extends JpaRepository<CampaignNote, Long> {
    Page<CampaignNote> findAllByCampaign_Id(Long campaignId, Pageable pageable);

}
