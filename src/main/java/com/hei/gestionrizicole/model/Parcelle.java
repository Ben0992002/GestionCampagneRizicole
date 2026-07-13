package com.hei.gestionrizicole.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String reference;

    private String localisation;

    @Positive
    @Column(nullable = false)
    private Double superficie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producteur_id", nullable = false)
    private Producteur producteur;
}
