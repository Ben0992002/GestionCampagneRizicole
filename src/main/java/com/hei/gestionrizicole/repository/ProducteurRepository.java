package com.hei.gestionrizicole.repository;

import com.hei.gestionrizicole.model.Producteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducteurRepository extends JpaRepository<Producteur, Integer> {
    Optional<Producteur> findByMatricule(String matricule);
    boolean existsByMatricule(String matricule);
}
