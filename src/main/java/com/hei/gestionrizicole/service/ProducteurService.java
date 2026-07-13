package com.hei.gestionrizicole.service;

import com.hei.gestionrizicole.dto.ProducteurDto;
import com.hei.gestionrizicole.exception.DuplicateResourceException;
import com.hei.gestionrizicole.exception.ResourceNotFoundException;
import com.hei.gestionrizicole.model.Producteur;
import com.hei.gestionrizicole.repository.ProducteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducteurService {

    private final ProducteurRepository producteurRepository;

    public List<Producteur> findAll() {
        return producteurRepository.findAll();
    }

    public Producteur findById(Integer id) {
        return producteurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producteur introuvable avec l'id : " + id));
    }

    public Producteur create(ProducteurDto dto) {
        if (producteurRepository.existsByMatricule(dto.getMatricule())) {
            throw new DuplicateResourceException("Un producteur avec le matricule '" + dto.getMatricule() + "' existe déjà");
        }
        Producteur producteur = Producteur.builder()
                .matricule(dto.getMatricule())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .telephone(dto.getTelephone())
                .build();
        return producteurRepository.save(producteur);
    }

    public Producteur update(Integer id, ProducteurDto dto) {
        Producteur producteur = findById(id);
        if (!producteur.getMatricule().equals(dto.getMatricule())
                && producteurRepository.existsByMatricule(dto.getMatricule())) {
            throw new DuplicateResourceException("Un producteur avec le matricule '" + dto.getMatricule() + "' existe déjà");
        }
        producteur.setMatricule(dto.getMatricule());
        producteur.setNom(dto.getNom());
        producteur.setPrenom(dto.getPrenom());
        producteur.setTelephone(dto.getTelephone());
        return producteurRepository.save(producteur);
    }

    public void delete(Integer id) {
        if (!producteurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producteur introuvable avec l'id : " + id);
        }
        producteurRepository.deleteById(id);
    }
}
