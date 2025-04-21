package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class HealthInsuranceCardDTO implements Serializable {
    private Long id;
    private int credentialNumber;
    private LocalDate expirationDate;
    private Long userId;
    private Long healthcareProviderId;

    public HealthInsuranceCardDTO() {
    }

    public HealthInsuranceCardDTO(Long id, int credentialNumber, LocalDate expirationDate, Long userId, Long healthcareProviderId) {
        this.id = id;
        this.credentialNumber = credentialNumber;
        this.expirationDate = expirationDate;
        this.userId = userId;
        this.healthcareProviderId = healthcareProviderId;
    }

    public HealthInsuranceCard newHealthCareInsuranceCard(User user,HealthcareProvider healthcareProvider){
        return new HealthInsuranceCard(this.id, this.credentialNumber, this.expirationDate, user, healthcareProvider);
    }

    public HealthInsuranceCardDTO update(HealthInsuranceCard healthInsuranceCard){
        Long userId = (this.userId == null)? healthInsuranceCard.getUser().getId() : this.userId;
        Long healthcareProviderId = (this.healthcareProviderId == null)? healthInsuranceCard.getHealthcareProvider().getId() : this.healthcareProviderId;
        return new HealthInsuranceCardDTO(healthInsuranceCard.getId(),healthInsuranceCard.getCredentialNumber(),healthInsuranceCard.getExpirationDate(),userId,healthcareProviderId);
    }

    public Long getId() {
        return id;
    }

    public int getCredentialNumber() {
        return credentialNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getHealthcareProviderId() {
        return healthcareProviderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCredentialNumber(int credentialNumber) {
        this.credentialNumber = credentialNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setHealthcareProviderId(Long healthcareProviderId) {
        this.healthcareProviderId = healthcareProviderId;
    }
}
