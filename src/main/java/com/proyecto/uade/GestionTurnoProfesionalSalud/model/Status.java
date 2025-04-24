package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String value;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    //Constructors
    public Status() {
    }

    public Status(Long id, String value) {
        this.id = id;
        this.value = value;
        this.appointments = new HashSet<>();
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
