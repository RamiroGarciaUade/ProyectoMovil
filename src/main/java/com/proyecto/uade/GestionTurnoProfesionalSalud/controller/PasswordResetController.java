package com.proyecto.uade.GestionTurnoProfesionalSalud.controller;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import com.proyecto.uade.GestionTurnoProfesionalSalud.repository.IUserRepository;
import com.proyecto.uade.GestionTurnoProfesionalSalud.service.PasswordResetService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/user/password-reset")
public class PasswordResetController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordResetService passwordResetService;

    // ✅ 1° Paso: enviar código por mail
    @PostMapping("/request")
    public String requestPasswordReset(@RequestBody EmailRequest emailRequest) throws MessagingException {
        Optional<User> userOptional = userRepository.findByEmail(emailRequest.getEmail());
        if (userOptional.isEmpty()) {
            return "No existe un usuario con ese email";
        }

        User user = userOptional.get();
        String code = passwordResetService.generateResetCode();

        // Guardar el código y su expiración
        user.setResetCode(code);
        user.setResetCodeExpiration(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);

        // Enviar el mail
        passwordResetService.sendResetEmail(user.getEmail(), code);

        return "Código enviado correctamente al correo";
    }

    // ✅ DTO para recibir el email
    public static class EmailRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
    @PostMapping("/verify")
public String verifyResetCode(@RequestBody VerifyRequest request) {
    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    User user = userOptional.get();

    if (user.getResetCode() == null || user.getResetCodeExpiration() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay código generado");
    }

    if (!user.getResetCode().equals(request.getCode())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código incorrecto");
    }

    if (user.getResetCodeExpiration().isBefore(LocalDateTime.now())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código expirado");
    }

    return "Código válido";
}

public static class VerifyRequest {
    private String email;
    private String code;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}

@PostMapping("/confirm")
public String confirmPasswordReset(@RequestBody ConfirmRequest request) {
    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    if (userOptional.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    User user = userOptional.get();

    // ✅ (Opcional) Podrías agregar una validación para que solo pueda cambiar la contraseña si el código ya fue verificado.
    if (user.getResetCode() == null || user.getResetCodeExpiration() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se solicitó reseteo de contraseña");
    }

    // ✅ Hashear la nueva contraseña
    String hashedPassword = passwordResetService.encodePassword(request.getNewPassword());
    user.setPassword(hashedPassword);

    // ✅ Borrar el código y expiración
    user.setResetCode(null);
    user.setResetCodeExpiration(null);

    userRepository.save(user);

    return "Contraseña actualizada correctamente";
}

// ✅ DTO para recibir los datos
public static class ConfirmRequest {
    private String email;
    private String newPassword;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}


}