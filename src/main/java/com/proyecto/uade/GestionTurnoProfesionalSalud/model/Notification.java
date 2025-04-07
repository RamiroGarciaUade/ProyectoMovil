package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Notification {
    private Long id;
    private User user;
    private Appointment appointment;
    private String message;
    private LocalDateTime datetime;
}
