package com.tejko.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.models.general.payload.requests.LoginRequest;
import com.tejko.models.general.payload.requests.RegisterRequest;
import com.tejko.models.general.payload.responses.JwtResponse;
import com.tejko.models.general.payload.responses.MessageResponse;
import com.tejko.api.services.AuthService;
import com.tejko.exceptions.UsernameTakenException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest)
            throws UsernameTakenException {
        return new ResponseEntity<>(new MessageResponse("Registration", authService.register(registerRequest)),
                HttpStatus.OK);
    }

}