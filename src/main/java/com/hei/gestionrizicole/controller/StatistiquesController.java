package com.hei.gestionrizicole.controller;

import com.hei.gestionrizicole.dto.StatistiquesDto;
import com.hei.gestionrizicole.service.StatistiquesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StatistiquesController {

    private final StatistiquesService statistiquesService;

    @GetMapping("/statistiques")
    public ResponseEntity<StatistiquesDto> getStatistiques() {
        return ResponseEntity.ok(statistiquesService.getStatistiques());
    }
}
