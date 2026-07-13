package com.hei.gestionrizicole.repository;

import com.hei.gestionrizicole.model.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne, Integer> {
    Optional<Campagne> findByAnneeAndSaison(Integer annee, String saison);
    boolean existsByAnneeAndSaison(Integer annee, String saison);
}
