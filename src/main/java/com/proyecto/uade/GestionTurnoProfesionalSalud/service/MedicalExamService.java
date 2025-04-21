package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.MedicalExamDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.MedicalExam;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IAppointmentRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IMedicalExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class MedicalExamService implements IService<MedicalExam, MedicalExamDTO> {
    private IMedicalExamRepository iMedicalExamRepository;
    private IAppointmentRepository iAppointmentRepository;
    @Autowired
    public MedicalExamService(IMedicalExamRepository iMedicalExamRepository, IAppointmentRepository iAppointmentRepository) {
        this.iMedicalExamRepository = iMedicalExamRepository;
        this.iAppointmentRepository = iAppointmentRepository;
    }

    @Override
    public List<MedicalExam> list() {
        return iMedicalExamRepository.findAll();
    }

    @Override
    public MedicalExam save(MedicalExamDTO exam) {
        Appointment appointment = iAppointmentRepository.findById(exam.getAppointmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MedicalExam m = exam.newMedicalExam(appointment);
        return iMedicalExamRepository.save(m);
    }

    @Override
    public MedicalExam find(Long id) {
        return iMedicalExamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iMedicalExamRepository.deleteById(id);
    }

    @Override
    public MedicalExam update(Long id, MedicalExamDTO dto) {
        MedicalExam exam = this.find(id);
        return this.save(dto.update(exam));
    }
}
