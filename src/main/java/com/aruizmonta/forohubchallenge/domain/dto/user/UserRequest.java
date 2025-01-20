package com.aruizmonta.forohubchallenge.domain.dto.user;

import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

    public User toEntity(String password, Set<Role> roles) {
        User entity = new User();
        entity.setEmail(email);
        entity.setName(name);
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setAssignedRoles(roles);
        return entity;
    }
}
