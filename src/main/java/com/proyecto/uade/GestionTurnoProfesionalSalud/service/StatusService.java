package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.StatusDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Status;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StatusService implements IService<Status, StatusDTO>{
    private IStatusRepository iStatusRepository;

    @Autowired
    public StatusService(IStatusRepository iStatusRepository){
        this.iStatusRepository= iStatusRepository;
    }

    @Override
    public List<Status> list() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status save(StatusDTO status) {
        Status s = new Status(status.getId(), status.getValue());
        return iStatusRepository.save(s);
    }

    @Override
    public Status find(Long id) {
        return iStatusRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iStatusRepository.deleteById(id);
    }

    @Override
    public Status update(Long id, StatusDTO dto) {
        Status status = this.find(id);
        return this.save(dto.update(status));
    }
}
