package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.UserDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements IService<User, UserDTO> {
    private IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<User> list() {
        return iUserRepository.findAll();
    }

    @Override
    public User save(UserDTO user) {
        if (iUserRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        User u = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
        return iUserRepository.save(u);
    }

    @Override
    public User find(Long id) {
        return iUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        this.find(id);
        iUserRepository.deleteById(id);
    }

    @Override
    public User update(Long id, UserDTO dto) {
        User user = this.find(id);
        UserDTO updatedDto = dto.update(user);
        return iUserRepository.save(user);
    }

    public User login(String email, String password) {
        return iUserRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));
    }
}
