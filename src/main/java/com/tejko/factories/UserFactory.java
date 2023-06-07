package com.tejko.factories;

import java.util.HashSet;
import java.util.Set;

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

        String passwordHash = encoder.encode(userRequest.getPassword());        
        Set<Role> roles = new HashSet<Role>() {
            {
                add(roleRepository.findByLabel("USER").orElseThrow(() -> new RuntimeException("Role not found.")));
            }
        };

        return User.create(
            userRequest.getUsername(), 
            passwordHash, 
            roles, 
            userRequest.isTestUser()
        );
    }

}
