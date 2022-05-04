package matej.tejkogames.factories;

import java.time.LocalDateTime;
import java.util.HashSet;

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
        user.setCreatedDate(LocalDateTime.now());
        user.setRoles(new HashSet<Role>() {
            {
                add(roleRepository.findByLabel("USER")
                        .orElseThrow(() -> new RuntimeException("Role not found.")));
            }
        });
        return user;
    }

    public User createUserWithEncodedPassword(String username, String encodedPassword) {
        User user = new User(username, encodedPassword);
        user.setCreatedDate(LocalDateTime.now());
        user.setRoles(new HashSet<Role>() {
            {
                add(roleRepository.findByLabel("USER")
                        .orElseThrow(() -> new RuntimeException("Role not found.")));
            }
        });
        return user;
    }

}
