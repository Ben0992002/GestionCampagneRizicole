package com.hei.gestionrizicole.service;

import com.hei.gestionrizicole.dto.StatistiquesDto;
import com.hei.gestionrizicole.repository.ParcelleRepository;
import com.hei.gestionrizicole.repository.ProducteurRepository;
import com.hei.gestionrizicole.repository.RecolteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatistiquesService {

    private final ProducteurRepository producteurRepository;
    private final ParcelleRepository parcelleRepository;
    private final RecolteRepository recolteRepository;

    public StatistiquesDto getStatistiques() {
        long nombreProducteurs = producteurRepository.count();
        long nombreParcelles = parcelleRepository.count();

        double surfaceTotale = parcelleRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getSuperficie() != null ? p.getSuperficie() : 0.0)
                .sum();

        double productionTotale = recolteRepository.findAll()
                .stream()
                .mapToDouble(r -> r.getQuantiteKg() != null ? r.getQuantiteKg() : 0.0)
                .sum();

        return StatistiquesDto.builder()
                .nombreProducteurs(nombreProducteurs)
                .nombreParcelles(nombreParcelles)
                .surfaceTotale(surfaceTotale)
                .productionTotale(productionTotale)
                .build();
    }
}
