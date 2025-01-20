package com.aruizmonta.forohubchallenge.infrastructure.repositories.rol;


import com.aruizmonta.forohubchallenge.domain.entities.Role;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(@Size(min = 3, max = 50) String name);
}
