package com.aruizmonta.forohubchallenge.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {
    @NotBlank
    @Size(min = 6, max = 50)
    private String email;
    @NotBlank
    @Size(max = 255)
    private String password;
}
