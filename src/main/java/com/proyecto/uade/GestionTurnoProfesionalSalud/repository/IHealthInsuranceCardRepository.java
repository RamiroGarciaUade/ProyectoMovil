package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHealthInsuranceCardRepository extends JpaRepository<HealthInsuranceCard, Long> {
}
