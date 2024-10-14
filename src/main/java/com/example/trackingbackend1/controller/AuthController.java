package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.dto.JwtResponse;
import com.example.trackingbackend1.dto.LoginRequest;
import com.example.trackingbackend1.dto.MessageResponse;
import com.example.trackingbackend1.dto.SignupRequest;
import com.example.trackingbackend1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        boolean userExists = authService.existsByUsername(signUpRequest.getUsername());
        boolean emailExists = authService.existsByEmail(signUpRequest.getEmail());

        if (userExists) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (emailExists) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        authService.registerUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            authService.blacklistToken(token);
            return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid token."));
        }
    }


}

