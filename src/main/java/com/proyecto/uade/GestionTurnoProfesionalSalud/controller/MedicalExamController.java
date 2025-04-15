package com.example.medicalsystem.controller;

import com.example.medicalsystem.model.MedicalExam;
import com.example.medicalsystem.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalExams")
public class MedicalExamController {

    @Autowired
    private MedicalExamService medicalExamService;

    @GetMapping
    public ResponseEntity<List<MedicalExam>> getAllMedicalExams() {
        return ResponseEntity.ok(medicalExamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalExam> getMedicalExamById(@PathVariable Long id) {
        MedicalExam exam = medicalExamService.getById(id);
        return exam != null ? ResponseEntity.ok(exam) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MedicalExam> createMedicalExam(@RequestBody MedicalExam exam) {
        return ResponseEntity.status(201).body(medicalExamService.create(exam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalExam> updateMedicalExam(@PathVariable Long id, @RequestBody MedicalExam exam) {
        MedicalExam updated = medicalExamService.update(id, exam);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalExam(@PathVariable Long id) {
        boolean deleted = medicalExamService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}