package com.proyecto.uade.GestionTurnoProfesionalSalud.dto.command;

import com.proyecto.uade.GestionTurnoProfesionalSalud.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    public static final int NAME_MAX_LENGHT = 50;

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private Integer phoneNumber;

    private String email;

    public UserDTO() {}

    public UserDTO(Long id, String firstName, String lastName, String password, Integer phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User newUser(String email) {
        return new User(this.id, this.firstName, this.lastName, email, this.password, this.phoneNumber);
    }

    public UserDTO update(User user) {
        if (this.firstName != null && this.firstName.length() <= NAME_MAX_LENGHT)
            user.setFirstName(this.firstName);
        if (this.lastName != null && this.lastName.length() <= NAME_MAX_LENGHT)
            user.setLastName(this.lastName);
        if (this.password != null && this.password.length() >= 6)
            user.setPassword(this.password);
        if (this.phoneNumber != null)
            user.setPhoneNumber(this.phoneNumber);

        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getPhoneNumber()
        );
    }

    public String getEmail() {
        return email;
    }
    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

