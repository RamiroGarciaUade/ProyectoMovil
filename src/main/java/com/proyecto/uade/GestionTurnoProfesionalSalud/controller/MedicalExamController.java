package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.MedicalExamDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.MedicalExam;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalExam")
public class MedicalExamController {
    @Autowired
    private MedicalExamService medicalExamService;

    @PostMapping
    public ResponseEntity<MedicalExam> save(@RequestBody MedicalExamDTO exam) {
        return ResponseEntity.ok(medicalExamService.save(exam));
    }

    @GetMapping
    public ResponseEntity<List<MedicalExam>>  list() {
        return ResponseEntity.ok(medicalExamService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalExam> find(@PathVariable Long id) {
        MedicalExam exam = medicalExamService.find(id);
        return ResponseEntity.ok(exam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalExamService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalExam> update(@PathVariable( name = "id") Long id, @RequestBody MedicalExamDTO exam) {
        return ResponseEntity.ok(medicalExamService.update(id, exam));
    }
}
