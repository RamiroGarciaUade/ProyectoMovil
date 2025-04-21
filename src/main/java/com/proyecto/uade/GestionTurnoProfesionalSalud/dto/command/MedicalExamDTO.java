package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.MedicalExam;

import java.io.Serializable;

public class MedicalExamDTO implements Serializable {
    public static final int NAME_MAX_LENGTH = 100;

    private Long id;
    private String name;
    private Long appointmentId;

    public MedicalExamDTO() {
    }

    public MedicalExamDTO(Long id, String name, Long appointmentId) {
        this.id = id;
        this.name = name;
        this.appointmentId = appointmentId;
    }

    public MedicalExam newMedicalExam(Appointment appointment){
        return new MedicalExam(this.id, this.name, appointment);
    }

    public MedicalExamDTO update(MedicalExam exam){
        if(this.name!= null && name.length() <= NAME_MAX_LENGTH)
            exam.setName(this.name);
        Long appointmentId = (this.appointmentId == null)? exam.getAppointment().getId() : this.appointmentId;
        return new MedicalExamDTO(exam.getId(), exam.getName(), appointmentId);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
