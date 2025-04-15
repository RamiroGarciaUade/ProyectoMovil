package com.example.medicalsystem.service.impl;

import com.example.medicalsystem.model.MedicalExam;
import com.example.medicalsystem.repository.MedicalExamRepository;
import com.example.medicalsystem.service.MedicalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalExamServiceImpl implements MedicalExamService {

    @Autowired
    private MedicalExamRepository repository;

    @Override
    public List<MedicalExam> getAll() {
        return repository.findAll();
    }

    @Override
    public MedicalExam getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MedicalExam create(MedicalExam exam) {
        return repository.save(exam);
    }

    @Override
    public MedicalExam update(Long id, MedicalExam exam) {
        Optional<MedicalExam> existingExam = repository.findById(id);
        if (existingExam.isPresent()) {
            MedicalExam toUpdate = existingExam.get();
            toUpdate.setExamName(exam.getExamName());
            toUpdate.setAppointmentId(exam.getAppointmentId());
            toUpdate.setDiagnosticId(exam.getDiagnosticId());
            toUpdate.setImages(exam.getImages());
            return repository.save(toUpdate);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}