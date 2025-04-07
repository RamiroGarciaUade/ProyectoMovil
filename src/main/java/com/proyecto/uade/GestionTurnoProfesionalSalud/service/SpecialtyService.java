package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.SpecialtyDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Specialty;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.ISpecialtyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpecialtyService implements IService<Specialty, SpecialtyDTO> {
    private ISpecialtyRepository iSpecialtyRepository;

    public SpecialtyService(ISpecialtyRepository iSpecialtyRepository){
        this.iSpecialtyRepository = iSpecialtyRepository;
    }

    public Specialty findByNameSpecialty(String name){
        return iSpecialtyRepository.findByName(name);
    }

    @Override
    public List<Specialty> list() {
        return iSpecialtyRepository.findAll();
    }

    @Override
    public Specialty save(SpecialtyDTO dto) {
        Specialty s = new Specialty(dto.getId(), dto.getName());
        return iSpecialtyRepository.save(s);
    }

    @Override
    public Specialty find(Long id) {
        return iSpecialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iSpecialtyRepository.deleteById(id);
    }

    @Override
    public Specialty update(Long id, SpecialtyDTO dto) {
        Specialty specialty = this.find(id);
        return this.save(dto.update(specialty));
    }
}
