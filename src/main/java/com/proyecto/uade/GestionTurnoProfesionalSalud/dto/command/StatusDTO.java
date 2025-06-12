package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Status;

import java.io.Serializable;

public class StatusDTO implements Serializable {
    public static final int VALUE_MAX_LENGTH = 50;
    private Long id;
    private String value;

    public StatusDTO() {
    }

    public StatusDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Status newStatus(){
        return new Status(this.id, this.value);
    }

    public StatusDTO update(Status status){
        if (this.value!= null && value.length()<= VALUE_MAX_LENGTH)
            status.setValue(this.value);
        return new StatusDTO(status.getId(), status.getValue());
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
