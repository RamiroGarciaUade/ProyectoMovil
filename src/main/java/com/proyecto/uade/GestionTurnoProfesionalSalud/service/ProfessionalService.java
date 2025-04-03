package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.ProfessionalDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProfessionalService implements IService<Professional, ProfessionalDTO>{
    private IProfessionalRepository iProfessionalRepository;

    @Autowired
    public ProfessionalService(IProfessionalRepository iProfessionalRepository){
        this.iProfessionalRepository = iProfessionalRepository;
    }
    @Override
    public List<Professional> list() {

        return iProfessionalRepository.findAll();
    }

    @Override
    public Professional save(ProfessionalDTO dto) {
        Professional p = new Professional();
        return iProfessionalRepository.save(p);
    }


    @Override
    public Professional find(Long id) {

        return iProfessionalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iProfessionalRepository.deleteById(id);
    }

    @Override
    public Professional update(Long id, ProfessionalDTO dto) {
        Professional p = this.find(id);
        return null;
    }

}
