package com.pWaw.mghelper.DataManipulation.Repository;

import com.pWaw.mghelper.DataManipulation.Model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
