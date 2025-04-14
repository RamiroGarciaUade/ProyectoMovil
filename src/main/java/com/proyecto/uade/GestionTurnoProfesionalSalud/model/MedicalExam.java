package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="medicalexams")
public class MedicalExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "appointment_id",referencedColumnName = "id")
    private Appointment appointment;
}
