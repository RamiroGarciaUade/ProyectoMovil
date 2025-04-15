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

import java.util.List;

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
        User user = iUserRepository.findById(appointment.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Professional professional = iProfessionalRepository.findById(appointment.getProfessionalId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Status status = iStatusRepository.findById(appointment.getStatusId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
        return this.save(dto.update(appointment));
    }
}
