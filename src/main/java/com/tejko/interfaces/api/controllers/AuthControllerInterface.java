package com.tejko.interfaces.api.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejko.models.general.payload.requests.LoginRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.LoginResponse;

public interface AuthControllerInterface {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest);
}
