package com.proyecto.uade.GestionTurnoProfesionalSalud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El professional ya existe")
public class ProfessionalDuplicate extends Exception {
    
}
