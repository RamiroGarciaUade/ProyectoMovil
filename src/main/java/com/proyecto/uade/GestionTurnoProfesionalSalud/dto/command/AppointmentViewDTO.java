package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentViewDTO {

    private LocalDate date;
    private LocalTime startTime;
    private String professionalName;
    private String specialty;
    private String name;      // Campo name del turno
    private Long statusId;    // ID del estado
    private String statusValue; // Valor del estado, opcional

    public AppointmentViewDTO() {}

    public static AppointmentViewDTO from(Appointment appointment) {
        AppointmentViewDTO dto = new AppointmentViewDTO();
        dto.setDate(appointment.getDate());
        dto.setStartTime(appointment.getStartTime());
        dto.setProfessionalName(
            appointment.getProfessional().getFirstName() + " " + appointment.getProfessional().getLastName()
        );
        dto.setSpecialty(
            appointment.getProfessional().getSpecialty().getName()
        );
        dto.setName(appointment.getName());
        // Nuevos campos de status
        if (appointment.getStatus() != null) {
            dto.setStatusId(appointment.getStatus().getId());
            dto.setStatusValue(appointment.getStatus().getValue());
        }
        return dto;
    }

    // Getters y setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}