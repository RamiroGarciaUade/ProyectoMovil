package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="healthInsuranceCards")
public class HealthInsuranceCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int credentialNumber;
    @Column
    private LocalDate expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "healthcare_provider_id")
    private HealthcareProvider healthcareProvider;

    //Constructors
    public HealthInsuranceCard() {
    }

    public HealthInsuranceCard(Long id, int credentialNumber, LocalDate expirationDate, User user, HealthcareProvider healthcareProvider) {
        this.id = id;
        this.credentialNumber = credentialNumber;
        this.expirationDate = expirationDate;
        this.user = user;
        this.healthcareProvider = healthcareProvider;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public int getCredentialNumber() {
        return credentialNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public User getUser() {
        return user;
    }

    public HealthcareProvider getHealthcareProvider() {
        return healthcareProvider;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setCredentialNumber(int credentialNumber) {
        this.credentialNumber = credentialNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHealthcareProvider(HealthcareProvider healthcareProvider) {
        this.healthcareProvider = healthcareProvider;
    }
}
