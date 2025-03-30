package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Specialty {
    private int specialtyID;
    private String name;
}
