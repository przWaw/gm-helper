package com.pWaw.gmhelper.DataManipulation.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String campaignName;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image campaignImage;
}
