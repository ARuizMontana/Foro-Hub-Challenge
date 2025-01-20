package com.aruizmonta.forohubchallenge.infrastructure.repositories.user;

import com.aruizmonta.forohubchallenge.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
