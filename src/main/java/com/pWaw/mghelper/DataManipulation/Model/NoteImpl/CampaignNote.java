package com.pWaw.mghelper.DataManipulation.Model.NoteImpl;

import com.pWaw.mghelper.DataManipulation.Model.Campaign;
import com.pWaw.mghelper.DataManipulation.Model.Note;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("campaign")
public class CampaignNote extends Note {

    @ManyToOne
    private Campaign campaign;

}
