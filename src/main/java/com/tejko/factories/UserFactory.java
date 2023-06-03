package com.tejko.factories;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.RoleRepository;
import com.tejko.interfaces.factories.UserFactoryInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.UserRequest;

@Component
public class UserFactory implements UserFactoryInterface {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User getObject(UserRequest userRequest) {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setTestUser(userRequest.isTestUser());
        user.setCreatedDate(LocalDateTime.now());
        
        user.setRoles(new HashSet<Role>() {
            {
                add(roleRepository.findByLabel("USER").orElseThrow(() -> new RuntimeException("Role not found.")));
            }
        });

        return user;
    }

}
