package com.proyecto.uade.GestionTurnoProfesionalSalud.model.dto;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;

import lombok.Data;

@Data
public class ProfessionalRequest {
    private String firstName;
    private String lastName;
    private Specialty specialty;
}
