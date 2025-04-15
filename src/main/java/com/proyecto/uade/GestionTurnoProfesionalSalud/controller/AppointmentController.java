package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.AppointmentDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.Appointment;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.AppointmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody AppointmentDTO appointment){
        return ResponseEntity.ok(appointmentService.save(appointment));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> list(){
        return ResponseEntity.ok(appointmentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> find(@PathVariable Long id){
        Appointment appointment = appointmentService.find(id);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        appointmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> udpate(@PathVariable(name = "id") Long id, @RequestBody AppointmentDTO appointment) {
        return ResponseEntity.ok(appointmentService.update(id, appointment));
    }
}
