package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthInsuranceCardDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.HealthInsuranceCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthInsuranceCard")
public class HealthInsuranceCardController {

    @Autowired
    private HealthInsuranceCardService healthInsuranceCardService;

    @PostMapping
    public ResponseEntity<HealthInsuranceCard> save(@RequestBody HealthInsuranceCardDTO card) {
        return ResponseEntity.ok(healthInsuranceCardService.save(card));
    }

    @GetMapping
    public ResponseEntity<List<HealthInsuranceCard>> list() {
        return ResponseEntity.ok(healthInsuranceCardService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInsuranceCard> find(@PathVariable Long id) {
        return ResponseEntity.ok(healthInsuranceCardService.find(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        healthInsuranceCardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceCard> update(
            @PathVariable Long id,
            @RequestBody HealthInsuranceCardDTO dto
    ) {
        return ResponseEntity.ok(healthInsuranceCardService.update(id, dto));
    }

    // === añadida: actualizar cobertura médica del usuario ===
    @PutMapping("/{userId}/coverage")
    public ResponseEntity<HealthInsuranceCard> updateCoverage(
            @PathVariable Long userId,
            @RequestBody HealthInsuranceCardDTO dto
    ) {
        HealthInsuranceCard updated = healthInsuranceCardService.updateCoverageById(userId, dto);
        return ResponseEntity.ok(updated);
    }
}