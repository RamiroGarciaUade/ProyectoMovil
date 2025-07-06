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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IService<Appointment, AppointmentDTO> {

    private final IAppointmentRepository iAppointmentRepository;
    private final IUserRepository iUserRepository;
    private final IProfessionalRepository iProfessionalRepository;
    private final IStatusRepository iStatusRepository;

    @Autowired
    public AppointmentService(IAppointmentRepository iAppointmentRepository,
                              IUserRepository iUserRepository,
                              IProfessionalRepository iProfessionalRepository,
                              IStatusRepository iStatusRepository) {
        this.iAppointmentRepository = iAppointmentRepository;
        this.iUserRepository = iUserRepository;
        this.iProfessionalRepository = iProfessionalRepository;
        this.iStatusRepository = iStatusRepository;
    }

    @Override
    public List<Appointment> list() {
        return iAppointmentRepository.findAll().stream()
                .peek(this::updateStatusIfExpired)
                .collect(Collectors.toList());
    }

    public List<Appointment> listAvailable() {
        return iAppointmentRepository.findAll().stream()
                .peek(this::updateStatusIfExpired)
                .filter(a -> a.getStatus().getValue().equalsIgnoreCase("Disponible"))
                .filter(a -> !a.getDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public Appointment save(AppointmentDTO appointment) {
        if (appointment.getUserId() != null) {
            throw new IllegalArgumentException("No se puede asignar un usuario al crear un turno. El turno debe crearse disponible.");
        }
        Professional professional = iProfessionalRepository.findById(appointment.getProfessionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professional not found"));

        Status status = iStatusRepository.findByValue("Disponible")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status 'disponible' no encontrado"));

        Appointment a = appointment.newAppointment(professional, status);
        return iAppointmentRepository.save(a);
    }

    @Override
    public Appointment find(Long id) {
        Appointment appointment = iAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateStatusIfExpired(appointment);
        return appointment;
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
        return iAppointmentRepository.findByProfessional_Specialty_Id(specialtyId).stream()
                .peek(this::updateStatusIfExpired)
                .filter(a -> !(a.getStatus().getValue().equalsIgnoreCase("Disponible") && a.getDate().isBefore(LocalDate.now())))
                .collect(Collectors.toList());
    }

    public List<Appointment> getByProfessional(Long professionalId) {
        return iAppointmentRepository.findByProfessional_Id(professionalId).stream()
                .peek(this::updateStatusIfExpired)
                .filter(a -> !(a.getStatus().getValue().equalsIgnoreCase("Disponible") && a.getDate().isBefore(LocalDate.now())))
                .collect(Collectors.toList());
    }

    // === añadida: listar todos los turnos de un usuario ===
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return iAppointmentRepository.findByUserId(userId);
    }

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    private void updateStatusIfExpired(Appointment appointment) {
        if (appointment.getStatus().getValue().equalsIgnoreCase("Agendado")) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = appointment.getDate().atTime(appointment.getFinishTime());

            if (end.isBefore(now)) {
                Status finalizado = iStatusRepository.findByValue("Finalizado")
                        .orElseThrow(() -> new RuntimeException("Estado 'FINALIZADO' no encontrado"));
                appointment.setStatus(finalizado);
                iAppointmentRepository.save(appointment);
            }
        }
    }

    public List<Appointment> searchAppointments(String specialty, String professional, LocalDate date) {
        String normSpecialty = specialty != null ? normalize(specialty) : null;
        String normProfessional = professional != null ? normalize(professional) : null;

        return iAppointmentRepository.findAll().stream()
                .peek(this::updateStatusIfExpired)
                .filter(a -> !(a.getStatus().getValue().equalsIgnoreCase("Disponible") && a.getDate().isBefore(LocalDate.now())))
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

    public List<Appointment> getAppointmentsForUser(Long userId, String status, LocalDate referenceDate, String time) {
        LocalDate today = referenceDate != null ? referenceDate : LocalDate.now();

        return iAppointmentRepository.findAll().stream()
                .filter(a -> a.getUser() != null && a.getUser().getId().equals(userId))
                .peek(this::updateStatusIfExpired)
                .filter(a -> {
                    if (status != null) {
                        return normalize(a.getStatus().getValue()).equals(normalize(status));
                    }
                    return true;
                })
                .filter(a -> {
                    LocalDate date = a.getDate();
                    return switch (time) {
                        case "past" -> date.isBefore(today);
                        case "upcoming" -> !date.isBefore(today);
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }

    public Appointment schedule(Long appointmentId, Long userId) {
        Appointment appointment = iAppointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (appointment.getUser() != null) {
            throw new RuntimeException("El turno ya está agendado");
        }

        if (!appointment.getStatus().getValue().equalsIgnoreCase("Disponible")) {
            throw new RuntimeException("El turno no está disponible");
        }

        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Status agendado = iStatusRepository.findByValue("Agendado")
                .orElseThrow(() -> new RuntimeException("Estado 'AGENDADO' no encontrado"));

        appointment.setUser(user);
        appointment.setStatus(agendado);

        return iAppointmentRepository.save(appointment);
    }

    public Appointment cancel(Long appointmentId) {
        Appointment appointment = iAppointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (appointment.getUser() == null || !appointment.getStatus().getValue().equalsIgnoreCase("AGENDADO")) {
            throw new RuntimeException("Solo se pueden cancelar turnos agendados");
        }

        Status libre = iStatusRepository.findByValue("Disponible")
                .orElseThrow(() -> new RuntimeException("Estado 'LIBRE' no encontrado"));

        appointment.setUser(null);
        appointment.setStatus(libre);

        return iAppointmentRepository.save(appointment);
    }
}