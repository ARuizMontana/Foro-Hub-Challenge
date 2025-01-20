package com.aruizmonta.forohubchallenge.application.services;

import com.aruizmonta.forohubchallenge.domain.dto.user.UserRequest;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.entities.User;

import java.util.Optional;

public interface IUserService {
    User create(UserRequest request);
    Optional<User> login(String email);
    Optional<UserResponse> findByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
