package com.aruizmonta.forohubchallenge.infrastructure.repositories.user;

import com.aruizmonta.forohubchallenge.application.services.IUserService;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserRequest;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import com.aruizmonta.forohubchallenge.infrastructure.repositories.rol.RoleRepository;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.EntityNotFoundException;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.InvalidPasswordException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User create(UserRequest request) {
        validatePassword(request);
        Role userRole = roleRepository.findByName("user").orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword()), Set.of(userRole)));
    }

    @Override
    public Optional<User> login(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserResponse> findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return Optional.of(new UserResponse(user));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void validatePassword(UserRequest request) {
        if (!StringUtils.hasText(request.getPassword()) || !StringUtils.hasText(request.getRepeatPassword())) {
            throw new InvalidPasswordException("Password dont match");
        }

        if (!request.getPassword().equals(request.getRepeatPassword())) {
            throw new InvalidPasswordException("Password dont match");
        }
    }
}
