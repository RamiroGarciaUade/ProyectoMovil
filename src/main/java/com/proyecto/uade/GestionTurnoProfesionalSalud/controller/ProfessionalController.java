package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.uade.GestionTurnoProfesionalSalud.exceptions.ProfessionalDuplicate;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.ProfessionalService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("Professional")
public class ProfessionalController {
    @Autowired
    private ProfessionalService professionalService;

    @GetMapping("/professionales/")
    public List<Professional> getProfessional() {
        return professionalService.getProfessionales();
    }

    @GetMapping("/{id}")
    public Optional<Professional> getProfessionalById (@PathVariable Long id) {
        return professionalService.getProfessionalById(id);
    }

    @GetMapping("/professionalFirstName/{firstName}")
    public ResponseEntity<List<Professional>> getProfessionalByFirstName(@PathVariable String firstName) {
        List<Professional> professionales = professionalService.findByProfessionalFirstName(firstName);
        if (professionales != null) {
            return ResponseEntity.ok(professionales);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/professionalFirstName/{specialtyName}")
    public ResponseEntity<List<Professional>> getProfessionalBySpecialtyName(String name){
        List<Professional> professionales = professionalService.findBySpecialtyName(name);
        if (professionales != null) {
            return ResponseEntity.ok(professionales);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/AddProfessional")
     public ResponseEntity<?> createProfessional(@RequestBody Professional professional){
        try {
            // Intenta crear el usuario
            Professional createdProfessional = professionalService.createProfessional(professional);
            // Respuesta exitosa
            return ResponseEntity.ok(java.util.Map.of("firstName", createdProfessional.getFirstName()));
        } catch (ProfessionalDuplicate e) {
            // Manejar la excepción y devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error",e.getMessage()));
        }
     }

    @PutMapping("/EditProfessional/{id}")
    public ResponseEntity<Professional> updateUser(@PathVariable Long id, @RequestBody Professional updatedProfessional) {
        Optional<Professional> professionalOptional = professionalService.getProfessionalById(id);

        if (professionalOptional.isPresent()) {
            Professional existingProfessional = professionalOptional.get();

            existingProfessional.setFirstName(updatedProfessional.getFirstName());
            existingProfessional.setLastName(updatedProfessional.getLastName());
            existingProfessional.setSpecialty(updatedProfessional.getSpecialty());

            Professional savedProfessional = professionalService.updateProfessional(existingProfessional).getBody();

            return ResponseEntity.ok(savedProfessional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/DeleteProfessional/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable Long id) {
        return professionalService.deleteProfessionalById(id);
    }
}
