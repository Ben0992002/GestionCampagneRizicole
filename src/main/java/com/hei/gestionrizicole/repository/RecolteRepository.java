package com.hei.gestionrizicole.repository;

import com.hei.gestionrizicole.model.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Integer> {
    List<Recolte> findByParcelleId(Integer parcelleId);

    @Query("SELECT COALESCE(SUM(r.quantiteKg), 0) FROM Recolte r WHERE r.parcelle.id = :parcelleId")
    Double sumQuantiteByParcelleId(Integer parcelleId);
}
