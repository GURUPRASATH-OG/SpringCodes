package com.sudo.Blog.controllers;

import com.sudo.Blog.dtos.AuthResponse;
import com.sudo.Blog.dtos.LoginRequest;
import com.sudo.Blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest)
    {
        UserDetails userDetails=authenticationService.authenticateUser(
                    loginRequest.getEmail(),
                loginRequest.getPassword());
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse =AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(8640000)
                .build();
        return ResponseEntity.ok(authResponse);
    }
}
