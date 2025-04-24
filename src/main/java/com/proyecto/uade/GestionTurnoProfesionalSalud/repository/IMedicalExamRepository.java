package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.MedicalExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalExamRepository extends JpaRepository<MedicalExam, Long> {
}
