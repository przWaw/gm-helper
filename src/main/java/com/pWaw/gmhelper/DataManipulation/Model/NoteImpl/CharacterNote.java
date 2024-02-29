package com.pWaw.gmhelper.DataManipulation.Model.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.Character;
import com.pWaw.gmhelper.DataManipulation.Model.Note;
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
