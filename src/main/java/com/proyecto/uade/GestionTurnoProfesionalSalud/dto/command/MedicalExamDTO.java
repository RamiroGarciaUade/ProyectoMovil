package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.MedicalExam;

import java.io.Serializable;
import java.util.List;

public class MedicalExamDTO implements Serializable {
    public static final int NAME_MAX_LENGTH = 100;

    private Long id;
    private String name;
    private Long appointmentId;

    private List<String> images;

    public MedicalExamDTO() {
    }

    public MedicalExamDTO(Long id, String name, Long appointmentId) {
        this.id = id;
        this.name = name;
        this.appointmentId = appointmentId;
    }

    public MedicalExamDTO(Long id, String name, Long appointmentId, List<String> images) {
        this.id = id;
        this.name = name;
        this.appointmentId = appointmentId;
        this.images = images;
    }

    public MedicalExam newMedicalExam(Appointment appointment){
        return new MedicalExam(this.id, this.name, appointment);
    }

    public MedicalExam update(MedicalExam medicalExam) {
        if (this.name != null && this.name.length() <= NAME_MAX_LENGTH)
            medicalExam.setName(this.name);

        if (this.images != null)
            medicalExam.setImages(this.images);

        return medicalExam;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
