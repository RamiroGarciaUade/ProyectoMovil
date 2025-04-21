package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthInsuranceCardDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.HealthInsuranceCardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthInsuranceCard")
public class HealthInsuranceCardController {
    @Autowired
    private HealthInsuranceCardService healthInsuranceCardService;

    @PostMapping
    public ResponseEntity<HealthInsuranceCard> save(@RequestBody HealthInsuranceCardDTO card){
        return ResponseEntity.ok(healthInsuranceCardService.save(card));
    }

    @GetMapping
    public ResponseEntity<List<HealthInsuranceCard>> list(){
        return ResponseEntity.ok(healthInsuranceCardService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInsuranceCard> find(@PathVariable Long id){
        HealthInsuranceCard card = healthInsuranceCardService.find(id);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        healthInsuranceCardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceCard> update(@PathVariable(name = "id") Long id, @RequestBody HealthInsuranceCardDTO card){
        return ResponseEntity.ok(healthInsuranceCardService.update(id, card));
    }
}
