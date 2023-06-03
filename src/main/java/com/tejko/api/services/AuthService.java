package com.tejko.api.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.UserRepository;
import com.tejko.components.JwtComponent;
import com.tejko.exceptions.UsernameTakenException;
import com.tejko.factories.UserFactory;
import com.tejko.models.general.User;
import com.tejko.models.general.UserDetailsImpl;
import com.tejko.models.general.payload.requests.LoginRequest;
import com.tejko.models.general.payload.requests.RegisterRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.JwtResponse;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Resource
    UserFactory userFactory;

    @Autowired
    JwtComponent jwtComponent;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtComponent.generateJwt(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    public String register(RegisterRequest registerRequest) throws UsernameTakenException {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UsernameTakenException("Username is already taken!");
        }
        User user = userFactory.getObject(new UserRequest(registerRequest.getUsername(), registerRequest.getPassword()));
        userRepository.save(user);
        return "User " + user.getUsername() + " successfully registered.";
    }

}
