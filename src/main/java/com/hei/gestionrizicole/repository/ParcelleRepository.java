package com.hei.gestionrizicole.repository;

import com.hei.gestionrizicole.model.Parcelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelleRepository extends JpaRepository<Parcelle, Integer> {
    Optional<Parcelle> findByReference(String reference);
    boolean existsByReference(String reference);
    List<Parcelle> findByLocalisationContainingIgnoreCase(String localisation);
}
