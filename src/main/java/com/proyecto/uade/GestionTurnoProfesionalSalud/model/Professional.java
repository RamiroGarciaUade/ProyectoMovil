package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Professional {
    private Long professionalID;
    private String firstName;
    private String lastName;
    private Specialty specialty;
}
