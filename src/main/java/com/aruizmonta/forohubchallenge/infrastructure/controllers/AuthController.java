package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.auth.AuthenticationService;
import com.aruizmonta.forohubchallenge.domain.dto.auth.AuthenticationRequest;
import com.aruizmonta.forohubchallenge.domain.dto.auth.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.create(request));
    }
}
