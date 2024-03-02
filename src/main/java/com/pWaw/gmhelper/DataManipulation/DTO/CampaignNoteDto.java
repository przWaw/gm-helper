package com.pWaw.gmhelper.DataManipulation.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CampaignNoteDto extends NoteDto {
    private Long campaignId;
}
