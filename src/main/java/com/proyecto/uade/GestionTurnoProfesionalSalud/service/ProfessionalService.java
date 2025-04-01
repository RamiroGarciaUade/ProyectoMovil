package com.proyecto.uade.GestionTurnoProfesionalSalud.service;
import com.proyecto.uade.GestionTurnoProfesionalSalud.exceptions.ProfessionalDuplicate;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface ProfessionalService {
    public List<Professional> getProfessionales();

    public Optional<Professional> getProfessionalById(Long professionalId);

    public List<Professional> findByProfessionalFirstName(String firstName);

    public List<Professional> findBySpecialtyName(String name);

    public ResponseEntity<Professional> updateProfessional(Professional professional);

    public Professional createProfessional(Professional professional) throws ProfessionalDuplicate;

    public ResponseEntity<String> deleteProfessionalById(Long id);
}
