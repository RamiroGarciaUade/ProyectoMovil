package com.proyecto.uade.GestionTurnoProfesionalSalud.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

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