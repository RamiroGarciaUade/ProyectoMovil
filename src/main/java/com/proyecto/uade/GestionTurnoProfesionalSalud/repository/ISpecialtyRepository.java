package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISpecialtyRepository extends JpaRepository<Specialty,Long> {
    Specialty findByName(String name);
}
