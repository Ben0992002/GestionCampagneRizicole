package com.hei.gestionrizicole.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate dateRecolte;

    @Positive
    @Column(nullable = false)
    private Double quantiteKg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parcelle_id", nullable = false)
    private Parcelle parcelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campagne_id", nullable = false)
    private Campagne campagne;
}
