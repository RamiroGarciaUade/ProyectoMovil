package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
