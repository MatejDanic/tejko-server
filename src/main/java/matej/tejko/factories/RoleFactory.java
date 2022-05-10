package matej.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.RoleRepository;
import matej.tejko.interfaces.factories.RoleFactoryInterface;
import matej.tejko.models.general.Role;
import matej.tejko.models.general.payload.requests.RoleRequest;

@Component
public class RoleFactory implements RoleFactoryInterface {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Role create(RoleRequest objectRequest) {

        Role role = new Role();

        role.setId(objectRequest.getId());
        role.setLabel(objectRequest.getLabel());
        role.setDescription(objectRequest.getDescription());

        return role;
    }

}
