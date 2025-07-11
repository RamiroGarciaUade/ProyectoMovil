package com.proyecto.uade.GestionTurnoProfesionalSalud.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "professional_id", referencedColumnName = "id")
    private Professional professional;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime finishTime;

    @Column
    private String notes;

    @Column(name = "name")
    private String name; // 👈 nuevo campo

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToMany(mappedBy = "appointment")
    @JsonIgnore
    private Set<MedicalExam> medicalExams = new HashSet<>();

    // Constructores

    public Appointment() {
    }

    public Appointment(Long id, Professional professional, LocalDate date, LocalTime startTime, LocalTime finishTime, Status status) {
        this.id = id;
        this.professional = professional;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public Appointment(Long id, User user, Professional professional, LocalDate date, LocalTime startTime, LocalTime finishTime, String notes, Status status) {
        this.id = id;
        this.user = user;
        this.professional = professional;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.notes = notes;
        this.status = status;
    }

    public Appointment(Long id, User user, Professional professional, LocalDate date, LocalTime startTime, LocalTime finishTime, String notes, Status status, Set<MedicalExam> medicalExams) {
        this.id = id;
        this.user = user;
        this.professional = professional;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.notes = notes;
        this.status = status;
        this.medicalExams = medicalExams;
    }

    public Appointment(Long id, User user, Professional professional, LocalDate date, LocalTime startTime, LocalTime finishTime) {
        this.id = id;
        this.user = user;
        this.professional = professional;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Professional getProfessional() {
        return professional;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public String getNotes() {
        return notes;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Set<MedicalExam> getMedicalExams() {
        return medicalExams;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMedicalExams(Set<MedicalExam> medicalExams) {
        this.medicalExams = medicalExams;
    }
}