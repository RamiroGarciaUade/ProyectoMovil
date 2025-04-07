package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;


import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;

import javax.print.attribute.standard.MediaSize;
import java.io.Serial;
import java.io.Serializable;

public class SpecialtyDTO implements Serializable {
    public static final int NAME_MAX_LENGTH = 100;
    private Long id;
    private String name;

    //Constructors

    public SpecialtyDTO() {

    }

    public SpecialtyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpecialtyDTO update(Specialty specialty){
        if(this.name!= null && name.length()<= NAME_MAX_LENGTH)
            specialty.setName(this.name);
        return new SpecialtyDTO(specialty.getId(), specialty.getName());
    }
    //Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
