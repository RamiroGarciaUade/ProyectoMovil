package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

public class Appointment {
    private Long id;
    private User user;
    private Professional professional;
    private DateTime datetime;
    private String notes;
    private Status status;
    private MedicalExam medicalExam;
}