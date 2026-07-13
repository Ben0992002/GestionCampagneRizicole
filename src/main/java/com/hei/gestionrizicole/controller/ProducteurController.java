package com.hei.gestionrizicole.controller;

import com.hei.gestionrizicole.dto.ProducteurDto;
import com.hei.gestionrizicole.model.Producteur;
import com.hei.gestionrizicole.service.ProducteurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producteurs")
@RequiredArgsConstructor
public class ProducteurController {

    private final ProducteurService producteurService;

    @GetMapping
    public ResponseEntity<List<Producteur>> getAll() {
        return ResponseEntity.ok(producteurService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producteur> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(producteurService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Producteur> create(@Valid @RequestBody ProducteurDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(producteurService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producteur> update(@PathVariable Integer id, @Valid @RequestBody ProducteurDto dto) {
        return ResponseEntity.ok(producteurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        producteurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
