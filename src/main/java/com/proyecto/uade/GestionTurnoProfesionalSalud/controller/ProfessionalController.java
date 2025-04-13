package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.ProfessionalDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//pongo un comentario
// ejemplo
//ejemplo
@RestController
@RequestMapping("/professionals")
public class ProfessionalController {
    @Autowired
    private ProfessionalService professionalService;

    @PostMapping
    public ResponseEntity<Professional> save(@RequestBody ProfessionalDTO professional){
        return ResponseEntity.ok(professionalService.save(professional));
    }

    @GetMapping
    public ResponseEntity<List<Professional>> list(){
        return ResponseEntity.ok(professionalService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> find(@PathVariable Long id){
        Professional professional = professionalService.find(id);
        return ResponseEntity.ok(professional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        professionalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professional> update(@PathVariable(name = "id") Long id, @RequestBody ProfessionalDTO professional){
        return ResponseEntity.ok(professionalService.update(id,professional));
    }
}
