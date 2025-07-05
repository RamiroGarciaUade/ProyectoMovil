package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.UserDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.UserProfileDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.config.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IService<User, UserDTO> {

    @Autowired
    private final IUserRepository iUserRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtUtil;

    public UserService(IUserRepository iUserRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<User> list() {
        return iUserRepository.findAll();
    }

    @Override
    public User save(UserDTO userDTO) {
        if (iUserRepository.existsByEmailIgnoreCase(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        String encrypted = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                encrypted,
                userDTO.getPhoneNumber()
        );
        return iUserRepository.save(user);
    }

    @Override
    public User find(Long id) {
        return iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        find(id);
        iUserRepository.deleteById(id);
    }

    @Override
    public User update(Long id, UserDTO dto) {
        User user = find(id);
        dto.update(user);
        return iUserRepository.save(user);
    }

    public Map<String, String> login(String email, String password) {
        User user = iUserRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("token", token);
    }
   ///UserProfileDTO
    @Transactional
    public User updateProfileById(Long id, UserProfileDTO dto) {
        User user = find(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        String digits = dto.getPhoneNumber().replaceAll("\\D+", "");
        user.setPhoneNumber(Integer.parseInt(digits));
        return iUserRepository.save(user);
    }
}