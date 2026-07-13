package com.hei.gestionrizicole.service;

import com.hei.gestionrizicole.dto.CampagneDto;
import com.hei.gestionrizicole.exception.DuplicateResourceException;
import com.hei.gestionrizicole.exception.ResourceNotFoundException;
import com.hei.gestionrizicole.model.Campagne;
import com.hei.gestionrizicole.repository.CampagneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampagneService {

    private final CampagneRepository campagneRepository;

    public List<Campagne> findAll() {
        return campagneRepository.findAll();
    }

    public Campagne findById(Integer id) {
        return campagneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campagne introuvable avec l'id : " + id));
    }

    public Campagne create(CampagneDto dto) {
        if (campagneRepository.existsByAnneeAndSaison(dto.getAnnee(), dto.getSaison())) {
            throw new DuplicateResourceException("Une campagne avec l'année " + dto.getAnnee()
                    + " et la saison '" + dto.getSaison() + "' existe déjà");
        }
        Campagne campagne = Campagne.builder()
                .annee(dto.getAnnee())
                .saison(dto.getSaison())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .build();
        return campagneRepository.save(campagne);
    }

    public Campagne update(Integer id, CampagneDto dto) {
        Campagne campagne = findById(id);
        if ((!campagne.getAnnee().equals(dto.getAnnee()) || !campagne.getSaison().equals(dto.getSaison()))
                && campagneRepository.existsByAnneeAndSaison(dto.getAnnee(), dto.getSaison())) {
            throw new DuplicateResourceException("Une campagne avec l'année " + dto.getAnnee()
                    + " et la saison '" + dto.getSaison() + "' existe déjà");
        }
        campagne.setAnnee(dto.getAnnee());
        campagne.setSaison(dto.getSaison());
        campagne.setDateDebut(dto.getDateDebut());
        campagne.setDateFin(dto.getDateFin());
        return campagneRepository.save(campagne);
    }

    public void delete(Integer id) {
        if (!campagneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campagne introuvable avec l'id : " + id);
        }
        campagneRepository.deleteById(id);
    }
}
