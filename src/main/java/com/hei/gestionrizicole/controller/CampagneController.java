package com.hei.gestionrizicole.controller;

import com.hei.gestionrizicole.dto.CampagneDto;
import com.hei.gestionrizicole.model.Campagne;
import com.hei.gestionrizicole.service.CampagneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
@RequiredArgsConstructor
public class CampagneController {

    private final CampagneService campagneService;

    @GetMapping
    public ResponseEntity<List<Campagne>> getAll() {
        return ResponseEntity.ok(campagneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campagne> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(campagneService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Campagne> create(@Valid @RequestBody CampagneDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campagneService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campagne> update(@PathVariable Integer id, @Valid @RequestBody CampagneDto dto) {
        return ResponseEntity.ok(campagneService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        campagneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
