package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentViewDTO {

    private LocalDate date;
    private LocalTime startTime;
    private String professionalName;
    private String specialty;

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
        return dto;
    }

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
}