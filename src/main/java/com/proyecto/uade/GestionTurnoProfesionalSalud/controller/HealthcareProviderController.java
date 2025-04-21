package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthcareProviderDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.HealthcareProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthcareProvider")
public class HealthcareProviderController {
    @Autowired
    private HealthcareProviderService healthcareProviderService;

    @PostMapping
    public ResponseEntity<HealthcareProvider> save(@RequestBody HealthcareProviderDTO provider) {
        return ResponseEntity.ok(healthcareProviderService.save(provider));
    }

    @GetMapping
    public ResponseEntity<List<HealthcareProvider>>  list() {
        return ResponseEntity.ok(healthcareProviderService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthcareProvider> find(@PathVariable Long id) {
        HealthcareProvider provider = healthcareProviderService.find(id);
        return ResponseEntity.ok(provider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        healthcareProviderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthcareProvider> update(@PathVariable( name = "id") Long id, @RequestBody HealthcareProviderDTO provider) {
        return ResponseEntity.ok(healthcareProviderService.update(id, provider));
    }
}
