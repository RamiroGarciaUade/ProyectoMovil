package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.*;

@Entity
@Table(name="professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

}
