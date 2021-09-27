package matej.tejkogames.api.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.RoleRepository;
import matej.tejkogames.api.repositories.UserRepository;
import matej.tejkogames.exceptions.UsernameTakenException;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.UserDetailsImpl;
import matej.tejkogames.models.general.payload.requests.LoginRequest;
import matej.tejkogames.models.general.payload.requests.RegisterRequest;
import matej.tejkogames.models.general.payload.responses.JwtResponse;
import matej.tejkogames.utils.JwtUtil;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    public String register(RegisterRequest registerRequest) throws UsernameTakenException {
        if (userRepo.existsByUsername(registerRequest.getUsername()))
            throw new UsernameTakenException("Korisničko ime je već zauzeto!");

        User user = new User(registerRequest.getUsername(), encoder.encode(registerRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepo.findByLabel("USER").orElseThrow(() -> new RuntimeException("Role not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);
        return "User " + user.getUsername() + " successfully registered.";
    }

}
