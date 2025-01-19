package com.aruizmonta.forohubchallenge.domain.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationResponse implements Serializable {
    private String jwt;
}
