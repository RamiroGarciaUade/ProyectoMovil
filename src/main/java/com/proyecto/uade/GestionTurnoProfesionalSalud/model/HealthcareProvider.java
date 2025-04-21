package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.*;

@Entity
@Table(name="healthcareProviders")
public class HealthcareProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    //Constructors

    public HealthcareProvider() {
    }

    public HealthcareProvider(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
