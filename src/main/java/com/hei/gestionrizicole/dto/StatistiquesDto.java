package com.hei.gestionrizicole.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatistiquesDto {

    private long nombreProducteurs;
    private long nombreParcelles;
    private double surfaceTotale;
    private double productionTotale;
}
