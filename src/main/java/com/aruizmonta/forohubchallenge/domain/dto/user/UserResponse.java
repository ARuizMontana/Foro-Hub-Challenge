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
    private String email;
    private String name;
    private List<String> roles;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.roles = user.getAssignedRoles().stream().map(Role::getName).toList();
    }

}
