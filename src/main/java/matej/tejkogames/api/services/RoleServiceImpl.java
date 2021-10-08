package matej.tejkogames.api.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public List<Role> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return roleRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Role> getAllByIdIn(Set<Integer> idSet) {
        return roleRepository.findAllById(idSet);
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

        role.updateByRequest(requestBody);

        return roleRepository.save(role);
    }

    @Override
    public List<Role> updateAll(Map<Integer, RoleRequest> idRequestMap) {
        List<Role> roleList = getAllByIdIn(idRequestMap.keySet());

        for (Role role : roleList) {
            role.updateByRequest(idRequestMap.get(role.getId()));
        }

        return roleRepository.saveAll(roleList);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public void deleteAllById(Set<Integer> idSet) {
        roleRepository.deleteAllById(idSet);
    }

    public List<User> getUsersByRolesId(Integer id) {
        return userRepository.findAllByRolesId(id);
    }

}
