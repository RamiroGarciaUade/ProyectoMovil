package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private int phoneNumber;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

}
