package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import lombok.Data;

@Data
public class Professional {
    private Long id;
    private String firstName;
    private String lastName;
    private Specialty specialty;
}
