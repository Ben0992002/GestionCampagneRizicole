package com.hei.gestionrizicole.controller;

import com.hei.gestionrizicole.dto.ParcelleDto;
import com.hei.gestionrizicole.dto.RendementDto;
import com.hei.gestionrizicole.model.Parcelle;
import com.hei.gestionrizicole.service.ParcelleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcelles")
@RequiredArgsConstructor
public class ParcelleController {

    private final ParcelleService parcelleService;

    @GetMapping
    public ResponseEntity<List<Parcelle>> getAll() {
        return ResponseEntity.ok(parcelleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelle> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(parcelleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Parcelle> create(@Valid @RequestBody ParcelleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parcelleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcelle> update(@PathVariable Integer id, @Valid @RequestBody ParcelleDto dto) {
        return ResponseEntity.ok(parcelleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        parcelleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/rendement")
    public ResponseEntity<RendementDto> getRendement(@PathVariable Integer id) {
        return ResponseEntity.ok(parcelleService.calculerRendement(id));
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Parcelle>> searchByLocalisation(@RequestParam String localisation) {
        return ResponseEntity.ok(parcelleService.rechercherParLocalisation(localisation));
    }
}
