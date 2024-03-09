package com.pWaw.gmhelper.DataManipulation.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "image_id")
    private Image characterPortrait;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Campaign campaign;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> characterDescription;

}
