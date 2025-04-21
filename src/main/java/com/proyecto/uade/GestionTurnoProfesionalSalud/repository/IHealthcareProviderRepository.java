package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long>{
}
