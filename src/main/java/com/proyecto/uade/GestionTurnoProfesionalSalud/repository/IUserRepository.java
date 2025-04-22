package com.proyecto.uade.GestionTurnoProfesionalSalud.repository;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);
}
