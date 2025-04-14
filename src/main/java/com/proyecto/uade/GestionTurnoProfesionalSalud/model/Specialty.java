package com.proyecto.uade.GestionTurnoProfesionalSalud.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "specialty")
    @JsonIgnore
    private Set<Professional> professionals = new HashSet<>();


    //Constructor
    public Specialty(){

    }
    public Specialty(Long id, String name){
        this.id = id;
        this.name = name;
    }
    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //Setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}