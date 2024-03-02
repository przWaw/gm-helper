package com.pWaw.gmhelper.DataManipulation.Model.NoteImpl;

import com.pWaw.gmhelper.DataManipulation.Model.Campaign;
import com.pWaw.gmhelper.DataManipulation.Model.Note;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("campaign")
@Data
public class CampaignNote extends Note {

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

}
