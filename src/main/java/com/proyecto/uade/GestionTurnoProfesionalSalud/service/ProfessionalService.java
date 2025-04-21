package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.ProfessionalDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IProfessionalRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.ISpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessionalService implements IService<Professional, ProfessionalDTO>{
    private IProfessionalRepository iProfessionalRepository;
    private ISpecialtyRepository iSpecialtyRepository;

    @Autowired
    public ProfessionalService(IProfessionalRepository iProfessionalRepository, ISpecialtyRepository iSpecialtyRepository){
        this.iProfessionalRepository = iProfessionalRepository;
        this.iSpecialtyRepository = iSpecialtyRepository;
    }
    @Override
    public List<Professional> list() {
        return iProfessionalRepository.findAll();
    }

    @Override
    public Professional save(ProfessionalDTO professional){
        Specialty specialty = iSpecialtyRepository.findById(professional.getSpecialtyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Professional p = professional.newProfessional(specialty);
        return iProfessionalRepository.save(p);
    }


    @Override
    public Professional find(Long id) {
        return iProfessionalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Professional> listBySpecialty(String specialty){
        return iProfessionalRepository.findAllBySpecialty_Name(specialty.toLowerCase());
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iProfessionalRepository.deleteById(id);
    }

    @Override
    public Professional update(Long id, ProfessionalDTO dto) {
        Professional professional = this.find(id);
        return this.save(dto.update(professional));
    }

}
