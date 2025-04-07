package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import java.util.List;

import lombok.Data;

@Data
public class MedicalExam {
    private Long id;
    private List<String> images;
}
