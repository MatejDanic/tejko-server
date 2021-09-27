package matej.tejkogames.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.RoleRepository;
import matej.tejkogames.api.repositories.UserRepository;
import matej.tejkogames.interfaces.services.RoleService;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.RoleRequest;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Role getById(Integer id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(RoleRequest requestBody) {
        Role role = new Role();
        if (requestBody.getLabel() != null) {
            role.setLabel(requestBody.getLabel());
        }
        if (requestBody.getDescription() != null) {
            role.setDescription(requestBody.getDescription());
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateById(Integer id, RoleRequest requestBody) {
        Role role = getById(id);
        if (requestBody.getLabel() != null) {
            role.setLabel(requestBody.getLabel());
        }
        if (requestBody.getDescription() != null) {
            role.setDescription(requestBody.getDescription());
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public List<User> getUsersByRolesId(Integer id) {
        return userRepository.findAllByRolesId(id);
    }

}
