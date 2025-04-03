package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;


import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;

import java.io.Serializable;

public class ProfessionalDTO implements Serializable {
    public static final int FIRST_NAME_MAX_LENGTH = 50;
    public static final int LAST_NAME_MAX_LENGTH = 50;
    private Long id;
    private String firstName;
    private String lastName;
    private Long specialtyId;

    //Constructors
    public ProfessionalDTO(){

    }

    public ProfessionalDTO(Long id, String firstName, String lastName, Long specialtyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialtyId = specialtyId;
    }
    public Professional newProfessional(Specialty specialty){
        return new Professional(this.id,this.firstName,this.lastName,specialty);
    }

    public ProfessionalDTO update(Professional professional){
        if(this.firstName!= null && firstName.length()<= FIRST_NAME_MAX_LENGTH)
            professional.setFirstName(this.firstName);
        if(this.lastName!= null && lastName.length()<= LAST_NAME_MAX_LENGTH)
            professional.setLastName(this.lastName);
        Long specialtyId = ( this.specialtyId == null )? professional.getSpecialty().getId() : this.specialtyId;
        return new ProfessionalDTO(professional.getId(), professional.getFirstName(), professional.getLastName(), specialtyId);
    }
    //Getters

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }


    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }
}
