package com.hei.gestionrizicole.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendementDto {

    private Integer parcelleId;
    private Double superficie;
    private Double quantiteRecoltee;
    private Double rendement;
}
