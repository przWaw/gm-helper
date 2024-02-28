package com.pWaw.mghelper.DataManipulation.Model.NoteImpl;

import com.pWaw.mghelper.DataManipulation.Model.Character;
import com.pWaw.mghelper.DataManipulation.Model.Note;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@DiscriminatorValue("character")
@Data
public class CharacterNote extends Note {

    @ManyToOne
    private Character character;

}
