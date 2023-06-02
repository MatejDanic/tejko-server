package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.RoleRepository;
import com.tejko.interfaces.factories.RoleFactoryInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;

@Component
public class RoleFactory implements RoleFactoryInterface {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Role getObject(RoleRequest objectRequest) {
        Role role = new Role();

        role.setId(objectRequest.getId());
        role.setLabel(objectRequest.getLabel());
        role.setDescription(objectRequest.getDescription());
        
        return role;
    }

}
