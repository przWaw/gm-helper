package com.pWaw.mghelper.DataManipulation.DTO;

import com.pWaw.mghelper.DataManipulation.Model.CharacterType;
import lombok.Data;

@Data
public class CharacterDto {
    private Long id;
    private CharacterType characterType;
    private String characterName;
    private String characterAbstract;
    private byte[] characterPortrait;
    private String fileType;
    private Long campaignId;
}
