package com.pWaw.mghelper.DataManipulation.DTO;

import lombok.Data;

@Data
public class CampaignNoteDto {
    private Long id;
    private String content;
    private String contentAbstract;
    private Long campaignId;
}
