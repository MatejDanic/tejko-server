package com.tejko.api.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tejko.components.JwtComponent;
import com.tejko.exceptions.UsernameTakenException;
import com.tejko.interfaces.api.services.AuthServiceInterface;
import com.tejko.models.general.UserDetailsImpl;
import com.tejko.models.general.payload.requests.LoginRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.LoginResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtComponent jwtComponent;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtComponent.generateJwt(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet());
        return new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    @Override
    public UserResponse register(UserRequest userRequest) throws UsernameTakenException {
        return userService.create(userRequest);
    }

}
