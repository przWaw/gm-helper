package com.pWaw.mghelper.DataManipulation.Repository;

import com.pWaw.mghelper.DataManipulation.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
