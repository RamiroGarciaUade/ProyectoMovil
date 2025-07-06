package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByProfessional_Specialty_Id(Long specialtyId);
    List<Appointment> findByProfessional_Id(Long professionalId);

    // === añadido: todos los turnos de un usuario ===
    List<Appointment> findByUserId(Long userId);
}