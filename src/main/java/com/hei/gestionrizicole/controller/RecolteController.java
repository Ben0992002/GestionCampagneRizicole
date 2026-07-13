package com.hei.gestionrizicole.controller;

import com.hei.gestionrizicole.dto.RecolteDto;
import com.hei.gestionrizicole.model.Recolte;
import com.hei.gestionrizicole.service.RecolteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recoltes")
@RequiredArgsConstructor
public class RecolteController {

    private final RecolteService recolteService;

    @GetMapping
    public ResponseEntity<List<Recolte>> getAll() {
        return ResponseEntity.ok(recolteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recolte> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(recolteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Recolte> create(@Valid @RequestBody RecolteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recolteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recolte> update(@PathVariable Integer id, @Valid @RequestBody RecolteDto dto) {
        return ResponseEntity.ok(recolteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recolteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
