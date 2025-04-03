package com.proyecto.uade.GestionTurnoProfesionalSalud.model;
import jakarta.persistence.*;

@Entity
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // Getters and setters
}