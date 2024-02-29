package com.pWaw.gmhelper.DataManipulation.Model.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Model.Note;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@DiscriminatorValue("campaign")
@Data
public class CampaignNote extends Note {

    @ManyToOne
    private Campaign campaign;

}
