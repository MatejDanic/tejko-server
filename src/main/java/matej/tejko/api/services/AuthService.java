package matej.tejko.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import matej.tejko.api.repositories.UserRepository;
import matej.tejko.components.JwtComponent;
import matej.tejko.exceptions.UsernameTakenException;
import matej.tejko.factories.UserFactory;
import matej.tejko.models.general.User;
import matej.tejko.models.general.UserDetailsImpl;
import matej.tejko.models.general.payload.requests.LoginRequest;
import matej.tejko.models.general.payload.requests.RegisterRequest;
import matej.tejko.models.general.payload.requests.UserRequest;
import matej.tejko.models.general.payload.responses.JwtResponse;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFactory userFactory;

    @Autowired
    JwtComponent jwtComponent;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtComponent.generateJwt(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    public String register(RegisterRequest registerRequest) throws UsernameTakenException {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new UsernameTakenException("Username is already taken!");

        User user = userFactory
                .create(new UserRequest(registerRequest.getUsername(), registerRequest.getPassword(), false));
        userRepository.save(user);
        return "User " + user.getUsername() + " successfully registered.";
    }

}
