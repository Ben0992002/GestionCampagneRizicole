package com.hei.gestionrizicole.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecolteDto {

    private Integer id;

    @NotNull
    private LocalDate dateRecolte;

    @Positive
    @NotNull
    private Double quantiteKg;

    @NotNull
    private Integer parcelleId;

    @NotNull
    private Integer campagneId;
}
