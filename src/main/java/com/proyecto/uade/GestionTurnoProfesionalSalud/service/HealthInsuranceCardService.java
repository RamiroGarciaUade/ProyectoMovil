package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthInsuranceCardDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IHealthInsuranceCardRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IHealthcareProviderRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HealthInsuranceCardService implements IService<HealthInsuranceCard, HealthInsuranceCardDTO> {

    private final IHealthInsuranceCardRepository iHealthInsuranceCardRepository;
    private final IUserRepository iUserRepository;
    private final IHealthcareProviderRepository iHealthcareProviderRepository;

    @Autowired
    public HealthInsuranceCardService(
            IHealthInsuranceCardRepository iHealthInsuranceCardRepository,
            IUserRepository iUserRepository,
            IHealthcareProviderRepository iHealthcareProviderRepository
    ) {
        this.iHealthInsuranceCardRepository = iHealthInsuranceCardRepository;
        this.iUserRepository = iUserRepository;
        this.iHealthcareProviderRepository = iHealthcareProviderRepository;
    }

    @Override
    public List<HealthInsuranceCard> list() {
        return iHealthInsuranceCardRepository.findAll();
    }

    @Override
    public HealthInsuranceCard save(HealthInsuranceCardDTO dto) {
        User user = iUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        HealthcareProvider provider = iHealthcareProviderRepository.findById(dto.getHealthcareProviderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
        HealthInsuranceCard card = dto.newHealthCareInsuranceCard(user, provider);
        return iHealthInsuranceCardRepository.save(card);
    }

    @Override
    public HealthInsuranceCard find(Long id) {
        return iHealthInsuranceCardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarjeta no encontrada"));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iHealthInsuranceCardRepository.deleteById(id);
    }

    @Override
    public HealthInsuranceCard update(Long id, HealthInsuranceCardDTO dto) {
        HealthInsuranceCard card = this.find(id);

        if (dto.getCredentialNumber() != null) {
            card.setCredentialNumber(dto.getCredentialNumber());
        }
        if (dto.getExpirationDate() != null) {
            card.setExpirationDate(dto.getExpirationDate());
        }
        if (dto.getUserId() != null) {
            User user = iUserRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
            card.setUser(user);
        }
        if (dto.getHealthcareProviderId() != null) {
            HealthcareProvider provider = iHealthcareProviderRepository.findById(dto.getHealthcareProviderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
            card.setHealthcareProvider(provider);
        }

        return iHealthInsuranceCardRepository.save(card);
    }

    // === añadida: actualizar cobertura médica del usuario ===
    @Transactional
    public HealthInsuranceCard updateCoverageById(Long userId, HealthInsuranceCardDTO dto) {
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        HealthcareProvider provider = iHealthcareProviderRepository.findById(dto.getHealthcareProviderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado"));
        HealthInsuranceCard card = dto.newHealthCareInsuranceCard(user, provider);
        return iHealthInsuranceCardRepository.save(card);
    }
}