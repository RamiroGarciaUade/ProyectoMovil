package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.StatusDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Status;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.StatusService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<Status> save(@RequestBody StatusDTO status){
        return ResponseEntity.ok((statusService.save(status)));
    }

    @GetMapping
    public ResponseEntity<List<Status>> list(){
        return ResponseEntity.ok(statusService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> find(@PathVariable Long id){
        Status status = statusService.find(id);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        statusService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable(name = "id") Long id, @RequestBody StatusDTO status){
        return ResponseEntity.ok(statusService.update(id, status));
    }
}
