package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;

@Repository
public interface SpecialtyRepository extends JpaRepository<Professional , Long> {
    
}
