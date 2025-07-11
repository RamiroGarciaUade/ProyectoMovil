package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.AppointmentDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.AppointmentViewDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /** Crear un turno (disponible) */
    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody AppointmentDTO appointment) {
        Appointment created = appointmentService.save(appointment);
        return ResponseEntity.ok(created);
    }

    /** Listar todos los turnos (con actualización de status expirados) */
    @GetMapping
    public ResponseEntity<List<Appointment>> list() {
        return ResponseEntity.ok(appointmentService.list());
    }

    /** Obtener un turno por id */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> find(@PathVariable Long id) {
        Appointment appointment = appointmentService.find(id);
        return ResponseEntity.ok(appointment);
    }

    /** Eliminar un turno */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /** Actualizar datos de un turno */
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(
            @PathVariable Long id,
            @RequestBody AppointmentDTO dto
    ) {
        Appointment updated = appointmentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /** Listar turnos por especialidad */
    @GetMapping("/specialtyId/{specialtyId}")
    public ResponseEntity<List<Appointment>> getBySpecialty(@PathVariable Long specialtyId) {
        return ResponseEntity.ok(appointmentService.getBySpecialty(specialtyId));
    }

    /** Listar turnos por profesional */
    @GetMapping("/professionalId/{professionalId}")
    public ResponseEntity<List<Appointment>> getByProfessional(@PathVariable Long professionalId) {
        return ResponseEntity.ok(appointmentService.getByProfessional(professionalId));
    }

    /** Buscar turnos por parámetros (especialidad, profesional, fecha) */
    @GetMapping("/search")
    public ResponseEntity<List<Appointment>> searchAppointments(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String professional,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<Appointment> results = appointmentService.searchAppointments(specialty, professional, date);
        return ResponseEntity.ok(results);
    }

    /** Listar mis turnos (filtros opcionales: status, fecha de referencia, pasado/upcoming) */
    @GetMapping("/my-appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(
            @RequestParam Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referenceDate,
            @RequestParam(required = false, defaultValue = "all") String time
    ) {
        List<Appointment> results = appointmentService.getAppointmentsForUser(userId, status, referenceDate, time);
        return ResponseEntity.ok(results);
    }

    /** Agendar un turno para un usuario */
    @PutMapping("/{id}/schedule")
    public ResponseEntity<Appointment> scheduleAppointment(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        Appointment scheduled = appointmentService.schedule(id, userId);
        return ResponseEntity.ok(scheduled);
    }

    /** Cancelar un turno: quita el usuario y deja el turno como \"Disponible\" */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Appointment canceled = appointmentService.cancel(id);
        return ResponseEntity.ok(canceled);
    }

    /** Listar turnos aún disponibles (no expirados y status = Disponible) */
    @GetMapping("/available")
    public ResponseEntity<List<Appointment>> getAvailableAppointments() {
        return ResponseEntity.ok(appointmentService.listAvailable());
    }

    /** Listar todos los turnos de un usuario con DTO de vista */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentViewDTO>> getByUser(@PathVariable Long userId) {
        List<AppointmentViewDTO> turns = appointmentService
            .getAppointmentsByUserId(userId)
            .stream()
            .map(AppointmentViewDTO::from)
            .toList();
        return ResponseEntity.ok(turns);
    }
}