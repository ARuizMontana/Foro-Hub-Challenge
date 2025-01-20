package com.aruizmonta.forohubchallenge.domain.dto.user;

import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
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
