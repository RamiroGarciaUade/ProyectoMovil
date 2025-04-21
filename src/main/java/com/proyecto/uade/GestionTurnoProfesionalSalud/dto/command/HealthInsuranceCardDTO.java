package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class HealthInsuranceCardDTO implements Serializable {
    private Long id;
    private Integer credentialNumber;
    private LocalDate expirationDate;
    private Long userId;
    private Long healthcareProviderId;

    public HealthInsuranceCardDTO() {}

    public HealthInsuranceCardDTO(Long id, Integer credentialNumber, LocalDate expirationDate, Long userId, Long healthcareProviderId) {
        this.id = id;
        this.credentialNumber = credentialNumber;
        this.expirationDate = expirationDate;
        this.userId = userId;
        this.healthcareProviderId = healthcareProviderId;
    }

    public HealthInsuranceCard newHealthCareInsuranceCard(User user, HealthcareProvider provider) {
        return new HealthInsuranceCard(this.id, this.credentialNumber, this.expirationDate, user, provider);
    }

    public HealthInsuranceCardDTO update(HealthInsuranceCard healthInsuranceCard){
        Long userId = (this.userId == null)? healthInsuranceCard.getUser().getId() : this.userId;
        Long healthcareProviderId = (this.healthcareProviderId == null)? healthInsuranceCard.getHealthcareProvider().getId() : this.healthcareProviderId;
        return new HealthInsuranceCardDTO(healthInsuranceCard.getId(),healthInsuranceCard.getCredentialNumber(),healthInsuranceCard.getExpirationDate(),userId,healthcareProviderId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCredentialNumber() {
        return credentialNumber;
    }

    public void setCredentialNumber(Integer credentialNumber) {
        this.credentialNumber = credentialNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHealthcareProviderId() {
        return healthcareProviderId;
    }

    public void setHealthcareProviderId(Long healthcareProviderId) {
        this.healthcareProviderId = healthcareProviderId;
    }
}
