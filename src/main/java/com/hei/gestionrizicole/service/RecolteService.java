package com.hei.gestionrizicole.service;

import com.hei.gestionrizicole.dto.RecolteDto;
import com.hei.gestionrizicole.exception.ResourceNotFoundException;
import com.hei.gestionrizicole.model.Campagne;
import com.hei.gestionrizicole.model.Parcelle;
import com.hei.gestionrizicole.model.Recolte;
import com.hei.gestionrizicole.repository.RecolteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecolteService {

    private final RecolteRepository recolteRepository;
    private final ParcelleService parcelleService;
    private final CampagneService campagneService;

    public List<Recolte> findAll() {
        return recolteRepository.findAll();
    }

    public Recolte findById(Integer id) {
        return recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Récolte introuvable avec l'id : " + id));
    }

    public Recolte create(RecolteDto dto) {
        Parcelle parcelle = parcelleService.findById(dto.getParcelleId());
        Campagne campagne = campagneService.findById(dto.getCampagneId());
        Recolte recolte = Recolte.builder()
                .dateRecolte(dto.getDateRecolte())
                .quantiteKg(dto.getQuantiteKg())
                .parcelle(parcelle)
                .campagne(campagne)
                .build();
        return recolteRepository.save(recolte);
    }

    public Recolte update(Integer id, RecolteDto dto) {
        Recolte recolte = findById(id);
        Parcelle parcelle = parcelleService.findById(dto.getParcelleId());
        Campagne campagne = campagneService.findById(dto.getCampagneId());
        recolte.setDateRecolte(dto.getDateRecolte());
        recolte.setQuantiteKg(dto.getQuantiteKg());
        recolte.setParcelle(parcelle);
        recolte.setCampagne(campagne);
        return recolteRepository.save(recolte);
    }

    public void delete(Integer id) {
        if (!recolteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Récolte introuvable avec l'id : " + id);
        }
        recolteRepository.deleteById(id);
    }
}
