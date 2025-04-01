package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalRepository extends JpaRepository<Professional , Long> {
    List<Professional> findByProfessionalFirstName(String firstName); 
    List<Professional> findBySpecialty_Name(String name);
}