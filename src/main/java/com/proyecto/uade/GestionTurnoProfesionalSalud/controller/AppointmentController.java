package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.AppointmentDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.AppointmentService;
import org.apache.coyote.Response;
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

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody AppointmentDTO appointment){
        return ResponseEntity.ok(appointmentService.save(appointment));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> list(){
        return ResponseEntity.ok(appointmentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> find(@PathVariable Long id){
        Appointment appointment = appointmentService.find(id);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        appointmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody AppointmentDTO dto){
        return ResponseEntity.ok(appointmentService.update(id, dto));
    }

    @GetMapping("/specialtyId/{specialtyId}")
    public ResponseEntity<List<Appointment>> getBySpecialty(@PathVariable Long specialtyId) {
        return ResponseEntity.ok(appointmentService.getBySpecialty(specialtyId));
    }

    @GetMapping("/professionalId/{professionalId}")
    public ResponseEntity<List<Appointment>> getByProfessional(@PathVariable Long professionalId) {
        return ResponseEntity.ok(appointmentService.getByProfessional(professionalId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Appointment>> searchAppointments(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String professional,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<Appointment> results = appointmentService.searchAppointments(specialty, professional, date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/my-appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(
            @RequestParam Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referenceDate,
            @RequestParam(required = false, defaultValue = "all") String time // values: all, past, upcoming
    ) {
        List<Appointment> results = appointmentService.getAppointmentsForUser(userId, status, referenceDate, time);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}/schedule")
    public ResponseEntity<Appointment> scheduleAppointment(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        Appointment appointment = appointmentService.schedule(id, userId);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.cancel(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Appointment>> getAvailableAppointments() {
        return ResponseEntity.ok(appointmentService.listAvailable());
    }

}
