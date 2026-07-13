package com.hei.gestionrizicole.service;

import com.hei.gestionrizicole.dto.ParcelleDto;
import com.hei.gestionrizicole.dto.RendementDto;
import com.hei.gestionrizicole.exception.DuplicateResourceException;
import com.hei.gestionrizicole.exception.ResourceNotFoundException;
import com.hei.gestionrizicole.model.Parcelle;
import com.hei.gestionrizicole.model.Producteur;
import com.hei.gestionrizicole.repository.ParcelleRepository;
import com.hei.gestionrizicole.repository.RecolteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelleService {

    private final ParcelleRepository parcelleRepository;
    private final RecolteRepository recolteRepository;
    private final ProducteurService producteurService;

    public List<Parcelle> findAll() {
        return parcelleRepository.findAll();
    }

    public Parcelle findById(Integer id) {
        return parcelleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parcelle introuvable avec l'id : " + id));
    }

    public Parcelle create(ParcelleDto dto) {
        if (parcelleRepository.existsByReference(dto.getReference())) {
            throw new DuplicateResourceException("Une parcelle avec la référence '" + dto.getReference() + "' existe déjà");
        }
        Producteur producteur = producteurService.findById(dto.getProducteurId());
        Parcelle parcelle = Parcelle.builder()
                .reference(dto.getReference())
                .localisation(dto.getLocalisation())
                .superficie(dto.getSuperficie())
                .producteur(producteur)
                .build();
        return parcelleRepository.save(parcelle);
    }

    public Parcelle update(Integer id, ParcelleDto dto) {
        Parcelle parcelle = findById(id);
        if (!parcelle.getReference().equals(dto.getReference())
                && parcelleRepository.existsByReference(dto.getReference())) {
            throw new DuplicateResourceException("Une parcelle avec la référence '" + dto.getReference() + "' existe déjà");
        }
        Producteur producteur = producteurService.findById(dto.getProducteurId());
        parcelle.setReference(dto.getReference());
        parcelle.setLocalisation(dto.getLocalisation());
        parcelle.setSuperficie(dto.getSuperficie());
        parcelle.setProducteur(producteur);
        return parcelleRepository.save(parcelle);
    }

    public void delete(Integer id) {
        if (!parcelleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Parcelle introuvable avec l'id : " + id);
        }
        parcelleRepository.deleteById(id);
    }

    public RendementDto calculerRendement(Integer id) {
        Parcelle parcelle = findById(id);
        Double quantiteTotale = recolteRepository.sumQuantiteByParcelleId(id);
        Double rendement = quantiteTotale / parcelle.getSuperficie();
        return RendementDto.builder()
                .parcelleId(id)
                .superficie(parcelle.getSuperficie())
                .quantiteRecoltee(quantiteTotale)
                .rendement(rendement)
                .build();
    }

    public List<Parcelle> rechercherParLocalisation(String localisation) {
        return parcelleRepository.findByLocalisationContainingIgnoreCase(localisation);
    }
}
