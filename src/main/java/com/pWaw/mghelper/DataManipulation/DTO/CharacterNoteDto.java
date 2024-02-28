package com.pWaw.mghelper.DataManipulation.DTO;

import lombok.Data;

@Data
public class CharacterNoteDto {
    private Long id;
    private String content;
    private String contentAbstract;
    private Long characterId;
}
