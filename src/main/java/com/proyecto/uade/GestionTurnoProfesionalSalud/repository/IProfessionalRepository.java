package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfessionalRepository extends JpaRepository<Professional,Long> {
    List<Professional> findAllBySpecialty_Id(Long Id);
    List<Professional> findAllBySpecialty_Name(String name);
}
