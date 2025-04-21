package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;

public class HealthcareProviderDTO {
    public static final int NAME_MAX_LENGHT = 50;

    private Long id;
    private String name;

    public HealthcareProviderDTO() {
    }

    public HealthcareProviderDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public HealthcareProvider newHealthcareProvider(){
        return new HealthcareProvider(this.id, this.name);
    }

    public HealthcareProviderDTO update(HealthcareProvider provider){
        if(this.name!= null && name.length() <= NAME_MAX_LENGHT)
            provider.setName(this.name);
        return new HealthcareProviderDTO(provider.getId(), provider.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
