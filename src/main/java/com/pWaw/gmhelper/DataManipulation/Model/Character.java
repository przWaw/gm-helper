package com.pWaw.gmhelper.DataManipulation.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private CharacterType characterType;

    private String characterName;

    private String characterAbstract;

    @Embedded
    private Image characterPortrait;

    @ManyToOne(fetch = FetchType.LAZY)
    private Campaign campaign;

}
