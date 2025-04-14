package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;


public class Appointment {
    private Long id;
    private User user;
    private Professional professional;
    private LocalDateTime datetime;
    private String notes;
    private Status status;
    private MedicalExam medicalExam;
}