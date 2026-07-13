package com.hei.gestionrizicole.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelleDto {

    private Integer id;

    @NotBlank
    private String reference;

    private String localisation;

    @Positive
    @NotNull
    private Double superficie;

    @NotNull
    private Integer producteurId;
}
