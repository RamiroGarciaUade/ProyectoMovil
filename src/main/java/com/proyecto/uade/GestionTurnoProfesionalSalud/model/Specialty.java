package com.proyecto.uade.GestionTurnoProfesionalSalud.model;
import jakarta.persistence.*;
@Entity
@Table(name="specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
