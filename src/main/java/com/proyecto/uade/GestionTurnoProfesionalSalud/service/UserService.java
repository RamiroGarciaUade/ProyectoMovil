package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@service

public class UserService {
    @Autowired
    private IUserRepository UserRepository;

    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return UserRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}