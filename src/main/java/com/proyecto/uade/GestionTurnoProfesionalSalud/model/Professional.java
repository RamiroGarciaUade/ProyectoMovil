package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="professionales")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professionalID;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    
    @OneToOne
    @Column(nullable = false)
    private Specialty specialty;
}
