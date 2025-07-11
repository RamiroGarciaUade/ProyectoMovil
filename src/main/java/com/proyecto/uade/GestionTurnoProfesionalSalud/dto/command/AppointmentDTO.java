package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Status;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    public static final int NOTES_MAX_LENGTH = 5000;
    private Long id;
    private Long userId;
    private Long professionalId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String notes;
    private Long statusId;

    private String name; // 👈 nombre completo del profesional

    public AppointmentDTO() {}

    public AppointmentDTO(Long id, Long professionalId, LocalDate date, LocalTime startTime, LocalTime finishTime, String notes, Long statusId) {
        this.id = id;
        this.professionalId = professionalId;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.notes = notes;
        this.statusId = statusId;
    }

    public AppointmentDTO(Long id, Long professionalId, LocalDate date, LocalTime startTime, LocalTime finishTime) {
        this.id = id;
        this.professionalId = professionalId;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public AppointmentDTO(Long id, Long userId, Long professionalId, LocalDate date, LocalTime startTime, LocalTime finishTime) {
        this.id = id;
        this.userId = userId;
        this.professionalId = professionalId;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public AppointmentDTO(Long id, Long userId, Long professionalId, LocalDate date, LocalTime startTime, LocalTime finishTime, String notes, Long statusId) {
        this.id = id;
        this.userId = userId;
        this.professionalId = professionalId;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.notes = notes;
        this.statusId = statusId;
    }

    public Appointment newAppointment(Professional professional, Status forcedStatus) {
        return new Appointment(
                this.id,
                professional,
                this.date,
                this.startTime,
                this.finishTime,
                forcedStatus
        );
    }

    public Appointment update(Appointment appointment) {
        if (this.notes != null && !this.notes.isBlank() && notes.length() <= NOTES_MAX_LENGTH) {
            appointment.setNotes(this.notes);
        }

        if (this.date != null) {
            appointment.setDate(this.date);
        }

        if (this.startTime != null) {
            appointment.setStartTime(this.startTime);
        }

        if (this.finishTime != null) {
            appointment.setFinishTime(this.finishTime);
        }

        if (this.professionalId != null) {
            Professional professional = new Professional();
            professional.setId(this.professionalId);
            appointment.setProfessional(professional);
        }

        if (this.statusId != null) {
            Status status = new Status();
            status.setId(this.statusId);
            appointment.setStatus(status);
        }

        return appointment;
    }

    // Getters

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public Long getProfessionalId() { return professionalId; }

    public LocalDate getDate() { return date; }

    public LocalTime getStartTime() { return startTime; }

    public LocalTime getFinishTime() { return finishTime; }

    public String getNotes() { return notes; }

    public Long getStatusId() { return statusId; }

    public String getName() { return name; }

    // Setters

    public void setId(Long id) { this.id = id; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setProfessionalId(Long professionalId) { this.professionalId = professionalId; }

    public void setDate(LocalDate date) { this.date = date; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public void setFinishTime(LocalTime finishTime) { this.finishTime = finishTime; }

    public void setNotes(String notes) { this.notes = notes; }

    public void setStatusId(Long statusId) { this.statusId = statusId; }

    public void setName(String name) { this.name = name; }
}