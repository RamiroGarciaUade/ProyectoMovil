package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessionalRepository extends JpaRepository<Professional,Long> {
    List<Professional> findByFirstName(String firstName); // Si querés una lista
    List<Professional> findBySpecialty_Name(String name); // Specialty es una entidad con campo "name"
}
