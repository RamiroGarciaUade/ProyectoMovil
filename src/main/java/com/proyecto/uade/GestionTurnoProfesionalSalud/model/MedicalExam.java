package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicalExams")
public class MedicalExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    //Constructors
    public MedicalExam() {
    }

    public MedicalExam(Long id, String name, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.appointment = appointment;
        this.images = new ArrayList<>();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public Appointment getAppointment() {
        return appointment;
    }


    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}