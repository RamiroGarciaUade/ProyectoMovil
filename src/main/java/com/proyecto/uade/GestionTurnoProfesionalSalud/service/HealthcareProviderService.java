package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthcareProviderDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IHealthcareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HealthcareProviderService  implements IService<HealthcareProvider, HealthcareProviderDTO>{
    private IHealthcareProviderRepository iHealthcareProviderRepository;
    @Autowired
    public HealthcareProviderService(IHealthcareProviderRepository iHealthcareProviderRepository) {
        this.iHealthcareProviderRepository = iHealthcareProviderRepository;
    }

    @Override
    public List<HealthcareProvider> list() {
        return iHealthcareProviderRepository.findAll();
    }

    @Override
    public HealthcareProvider save(HealthcareProviderDTO provider) {
        HealthcareProvider p = provider.newHealthcareProvider();
        return iHealthcareProviderRepository.save(p);
    }

    @Override
    public HealthcareProvider find(Long id) {
        return iHealthcareProviderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iHealthcareProviderRepository.deleteById(id);
    }

    @Override
    public HealthcareProvider update(Long id, HealthcareProviderDTO dto) {
        HealthcareProvider provider = this.find(id);
        return this.save(dto.update(provider));
    }
}
