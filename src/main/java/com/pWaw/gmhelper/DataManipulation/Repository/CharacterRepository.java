package com.pWaw.gmhelper.DataManipulation.Repository;

import com.pWaw.gmhelper.DataManipulation.Model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Page<Character> findAllByCampaign_Id(Long id, Pageable pageable);
}
