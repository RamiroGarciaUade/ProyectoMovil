package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.AppointmentDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Professional;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Status;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IAppointmentRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IProfessionalRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IStatusRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IService<Appointment, AppointmentDTO>{
    private IAppointmentRepository iAppointmentRepository;
    private IUserRepository iUserRepository;
    private IProfessionalRepository iProfessionalRepository;
    private IStatusRepository iStatusRepository;

    public AppointmentService(IAppointmentRepository iAppointmentRepository, IUserRepository iUserRepository, IProfessionalRepository iProfessionalRepository, IStatusRepository iStatusRepository) {
        this.iAppointmentRepository = iAppointmentRepository;
        this.iUserRepository = iUserRepository;
        this.iProfessionalRepository = iProfessionalRepository;
        this.iStatusRepository = iStatusRepository;
    }

    @Autowired

    @Override
    public List<Appointment> list() {
        return iAppointmentRepository.findAll();
    }

    @Override
    public Appointment save(AppointmentDTO appointment) {
        User user = iUserRepository.findById(appointment.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Professional professional = iProfessionalRepository.findById(appointment.getProfessionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found"));

        // IGNORAR statusId del DTO y usar siempre el status "disponible"
        Status status = iStatusRepository.findByValue("Disponible")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status 'disponible' no encontrado"));

        Appointment a = appointment.newAppointment(user, professional, status);
        return iAppointmentRepository.save(a);
    }

    @Override
    public Appointment find(Long id) {
        return iAppointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iAppointmentRepository.deleteById(id);
    }

    @Override
    public Appointment update(Long id, AppointmentDTO dto) {
        Appointment appointment = this.find(id);

        if (dto.getNotes() != null && dto.getNotes().length() <= AppointmentDTO.NOTES_MAX_LENGTH) {
            appointment.setNotes(dto.getNotes());
        }

        if (dto.getStatusId() != null) {
            Status status = iStatusRepository.findById(dto.getStatusId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status no encontrado"));
            appointment.setStatus(status);
        }

        return iAppointmentRepository.save(appointment);
    }

    public List<Appointment> getBySpecialty(Long specialtyId) {
        return iAppointmentRepository.findByProfessional_Specialty_Id(specialtyId);
    }

    public List<Appointment> getByProfessional(Long professionalId) {
        return iAppointmentRepository.findByProfessional_Id(professionalId);
    }

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    public List<Appointment> searchAppointments(String specialty, String professional, LocalDate date) {
        String normSpecialty = specialty != null ? normalize(specialty) : null;
        String normProfessional = professional != null ? normalize(professional) : null;

        return iAppointmentRepository.findAll().stream()
                .filter(a -> {
                    boolean match = true;

                    if (normSpecialty != null) {
                        String spec = normalize(a.getProfessional().getSpecialty().getName());
                        match &= spec.equals(normSpecialty);
                    }

                    if (normProfessional != null) {
                        String first = normalize(a.getProfessional().getFirstName());
                        String last = normalize(a.getProfessional().getLastName());
                        match &= (first.contains(normProfessional) || last.contains(normProfessional));
                    }

                    if (date != null) {
                        match &= a.getDate().equals(date);
                    }

                    return match;
                })
                .collect(Collectors.toList());
    }


}
