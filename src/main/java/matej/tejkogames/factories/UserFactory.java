package matej.tejkogames.factories;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import matej.tejkogames.api.repositories.RoleRepository;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;

@Component
public class UserFactory {
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public User createUser(String username, String password) {
        User user = new User(username, encoder.encode(password));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByLabel("USER").orElseThrow(() -> new RuntimeException("Role not found."));
        roles.add(userRole);
        user.setRoles(roles);
        return user;
    }

    public User createUserWithEncodedPassword(String username, String encodedPassword) {
        User user = new User(username, encodedPassword);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByLabel("USER").orElseThrow(() -> new RuntimeException("Role not found."));
        roles.add(userRole);
        user.setRoles(roles);
        return user;
    }
    
}
