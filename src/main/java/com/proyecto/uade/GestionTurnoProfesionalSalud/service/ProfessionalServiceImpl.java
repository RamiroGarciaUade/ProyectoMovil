package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import com.proyecto.uade.GestionTurnoProfesionalSalud.exceptions.ProfessionalDuplicate;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.ProfessionalRepository;

@Service
public class ProfessionalServiceImpl implements  ProfessionalService {
    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public List<Professional> getProfessionales(){
        return professionalRepository.findAll();
    }
    @Override
    public Optional<Professional> getProfessionalById(Long professionalId){
        return professionalRepository.findById(professionalId);
    }
    @Override
    public List<Professional> findByProfessionalFirstName(String firstName){
        return professionalRepository.findByProfessionalFirstName(firstName);
    }
    @Override
    public List<Professional> findBySpecialtyName(String name){
        return professionalRepository.findBySpecialty_Name(name);
    }
    @Override
    public ResponseEntity<Professional> updateProfessional(Professional professional){
        Optional<Professional> existingProfessional = professionalRepository.findById(professional.getProfessionalID());

        if (existingProfessional.isPresent()) {
            Professional professionalToUpdate = existingProfessional.get();
            professionalToUpdate.setFirstName(professional.getFirstName());
            professionalToUpdate.setLastName(professional.getLastName());
            professionalToUpdate.setSpecialty(professional.getSpecialty());
            
            Professional updatedProfessional = professionalRepository.save(professionalToUpdate);

            return ResponseEntity.ok(updatedProfessional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public Professional createProfessional(Professional professional) throws ProfessionalDuplicate {
        Professional professionalCreate = professionalRepository.save(professional);
        return professionalCreate;
    }

    @Override
    public ResponseEntity<String> deleteProfessionalById(Long id){
        Optional<Professional> professional = professionalRepository.findById(id);

        if (professional.isPresent()) {
            professionalRepository.delete(professional.get());
            return ResponseEntity.ok("El professional ya fue borrado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el professional.");
        }
    }

}
