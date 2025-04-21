package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.SpecialtyDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public ResponseEntity<Specialty> save(@RequestBody SpecialtyDTO specialty){
        return ResponseEntity.ok(specialtyService.save(specialty));
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> list(){
        return ResponseEntity.ok(specialtyService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> find(@PathVariable Long id){
        Specialty specialty = specialtyService.find(id);
        return ResponseEntity.ok(specialty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        specialtyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialty> update(@PathVariable(name = "id") Long id, @RequestBody SpecialtyDTO specialty){
        return ResponseEntity.ok(specialtyService.update(id, specialty));
    }
}
