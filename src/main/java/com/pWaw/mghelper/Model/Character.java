package com.pWaw.mghelper.Model;

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

    private String shortDescription;

    @Lob
    private byte[] characterPortrait;

    @ManyToOne(fetch = FetchType.LAZY)
    private Campaign campaign;

}
