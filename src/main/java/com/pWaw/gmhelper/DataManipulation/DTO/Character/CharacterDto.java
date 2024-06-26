package com.pWaw.gmhelper.DataManipulation.DTO.Character;

import com.pWaw.gmhelper.DataManipulation.Model.CharacterType;
import lombok.Data;

import java.util.List;

@Data
public class CharacterDto {
    private Long id;
    private CharacterType characterType;
    private String characterName;
    private String characterAbstract;
    private Long portraitId;
    private Long campaignId;
    private List<String> characterDescription;
}
