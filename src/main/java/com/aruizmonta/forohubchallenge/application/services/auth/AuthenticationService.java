package com.aruizmonta.forohubchallenge.application.services.auth;

import com.aruizmonta.forohubchallenge.application.services.IUserService;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserRequest;
import com.aruizmonta.forohubchallenge.domain.dto.user.UserResponse;
import com.aruizmonta.forohubchallenge.domain.entities.Role;
import com.aruizmonta.forohubchallenge.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

    public UserResponse create(UserRequest user) {
        User register = userService.create(user);
        UserResponse response = new UserResponse(register);
        response.setJwt(jwtService.generateToken(register, generateExtraClaims(register)));
        return response;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", String.join(",", user.getRoles().stream().map(Role::getName).toList()));
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }
}
