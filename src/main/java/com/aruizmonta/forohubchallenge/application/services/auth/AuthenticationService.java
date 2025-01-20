package com.aruizmonta.forohubchallenge.application.services.auth;

import com.aruizmonta.forohubchallenge.application.services.IUserService;
import com.aruizmonta.forohubchallenge.domain.dto.auth.AuthenticationRequest;
import com.aruizmonta.forohubchallenge.domain.dto.auth.AuthenticationResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import com.aruizmonta.forohubchallenge.infrastructure.utils.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse create(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        User user = userService.login(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    public User findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getPrincipal().toString()).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", String.join(",", user.getAssignedRoles().stream().map(Role::getName).toList()));
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }
}
