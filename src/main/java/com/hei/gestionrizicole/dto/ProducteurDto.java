package com.hei.gestionrizicole.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducteurDto {

    private Integer id;

    @NotBlank
    private String matricule;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    private String telephone;
}
