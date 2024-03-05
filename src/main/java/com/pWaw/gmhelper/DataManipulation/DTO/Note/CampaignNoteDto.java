package com.pWaw.gmhelper.DataManipulation.DTO.Note;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CampaignNoteDto extends NoteDto {
    private Long campaignId;
}
