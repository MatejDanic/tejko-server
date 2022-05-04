package matej.tejkogames.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.UserRepository;
import matej.tejkogames.components.JwtComponent;
import matej.tejkogames.exceptions.UsernameTakenException;
import matej.tejkogames.factories.UserFactory;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.UserDetailsImpl;
import matej.tejkogames.models.general.payload.requests.LoginRequest;
import matej.tejkogames.models.general.payload.requests.RegisterRequest;
import matej.tejkogames.models.general.payload.responses.JwtResponse;

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

        User user = userFactory.createUser(registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.save(user);
        return "User " + user.getUsername() + " successfully registered.";
    }

}
