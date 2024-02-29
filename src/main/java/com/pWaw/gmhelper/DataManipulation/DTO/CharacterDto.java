package com.pWaw.gmhelper.DataManipulation.DTO;

import com.pWaw.gmhelper.DataManipulation.Model.CharacterType;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import lombok.Data;

@Data
public class CharacterDto {
    private Long id;
    private CharacterType characterType;
    private String characterName;
    private String characterAbstract;
    private Image characterPortrait;
    private Long campaignId;
}
