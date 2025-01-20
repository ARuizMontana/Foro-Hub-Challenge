package com.aruizmonta.forohubchallenge.infrastructure.controllers;

import com.aruizmonta.forohubchallenge.application.services.IUserService;
import com.aruizmonta.forohubchallenge.application.services.auth.AuthenticationService;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserRequest;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        UserResponse response = new UserResponse(userService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<UserResponse> findMyUser() {
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(new UserResponse(user));
    }
}
