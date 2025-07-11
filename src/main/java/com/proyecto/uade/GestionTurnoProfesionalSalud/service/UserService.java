package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.HealthInsuranceCardDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.UserDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command.UserProfileDTO;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthInsuranceCard;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.HealthcareProvider;
import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IHealthcareProviderRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IHealthInsuranceCardRepository;
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
    private final IHealthInsuranceCardRepository cardRepository;

    @Autowired
    private final IHealthcareProviderRepository providerRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private final JwtUtil jwtUtil;

    public UserService(IUserRepository iUserRepository,
                       IHealthInsuranceCardRepository cardRepository,
                       IHealthcareProviderRepository providerRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.iUserRepository = iUserRepository;
        this.cardRepository = cardRepository;
        this.providerRepository = providerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Devuelve todos los usuarios.
     */
    @Override
    public List<User> list() {
        return iUserRepository.findAll();
    }

    /**
     * Registra un nuevo usuario cifrando la contraseña.
     */
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

    /**
     * Busca un usuario por ID o lanza 404.
     */
    @Override
    public User find(Long id) {
        return iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Elimina un usuario existente.
     */
    @Override
    public void delete(Long id) {
        find(id);
        iUserRepository.deleteById(id);
    }

    /**
     * Actualiza campos del usuario según UserDTO.
     */
    @Override
    public User update(Long id, UserDTO dto) {
        User user = find(id);
        dto.update(user);
        return iUserRepository.save(user);
    }

    /**
     * Autentica y genera JWT.
     */
    public Map<String, Object> login(String email, String password) {
    User user = iUserRepository.findByEmail(email)
            .filter(u -> passwordEncoder.matches(password, u.getPassword()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

    String token = jwtUtil.generateToken(user.getEmail());

    return Map.of(
            "token", token,
            "user", user
    );
}

    /**
     * Actualiza nombre, apellido y teléfono (mis datos).
     */
    @Transactional
    public User updateProfileById(Long id, UserProfileDTO dto) {
        User user = find(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        String digits = dto.getPhoneNumber().replaceAll("\\D+", "");
        user.setPhoneNumber(Integer.parseInt(digits));
        return iUserRepository.save(user);
    }

    /**
     * Actualiza la cobertura médica del usuario.
     */
    @Transactional
    public HealthInsuranceCard updateCoverageById(Long userId, HealthInsuranceCardDTO dto) {
        User user = find(userId);
        HealthcareProvider provider = providerRepository.findById(dto.getHealthcareProviderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        HealthInsuranceCard card = dto.newHealthCareInsuranceCard(user, provider);
        return cardRepository.save(card);
    }
}