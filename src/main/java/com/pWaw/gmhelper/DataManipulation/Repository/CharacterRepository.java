package com.pWaw.gmhelper.DataManipulation.Repository;

import com.pWaw.gmhelper.DataManipulation.Model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
