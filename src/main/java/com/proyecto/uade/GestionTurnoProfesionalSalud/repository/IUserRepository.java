package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
