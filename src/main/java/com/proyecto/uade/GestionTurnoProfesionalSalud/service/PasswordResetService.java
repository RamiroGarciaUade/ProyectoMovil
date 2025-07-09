package com.proyecto.uade.GestionTurnoProfesionalSalud.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordResetService {

    @Autowired
    private JavaMailSender mailSender;

    private static final SecureRandom random = new SecureRandom();

    // ✅ Generar código de 6 dígitos
    public String generateResetCode() {
        int code = 100000 + random.nextInt(900000); // Números entre 100000 y 999999
        return String.valueOf(code);
    }

    // ✅ Enviar email
    public void sendResetEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Recuperación de contraseña");
        helper.setText("Tu código de recuperación es: " + code, true); // puede ser HTML si querés

        mailSender.send(message);
    }
    @Autowired
private BCryptPasswordEncoder passwordEncoder;

public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
}

}