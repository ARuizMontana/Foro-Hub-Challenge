package com.aruizmonta.forohubchallenge.domain.dto.user;

import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserResponse implements Serializable {
    private Long id;
    private String email;
    private String name;
    private List<String> roles;
    private String jwt;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.roles = user.getRoles().stream().map(Role::getName).toList();
    }

}
