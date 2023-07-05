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
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.LoginResponse;
import com.tejko.api.services.AuthService;
import com.tejko.exceptions.UsernameTakenException;
import com.tejko.interfaces.api.controllers.AuthControllerInterface;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerInterface {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) throws UsernameTakenException {
        return new ResponseEntity<>(authService.register(userRequest), HttpStatus.OK);
    }

}