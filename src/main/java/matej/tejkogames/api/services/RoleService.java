package matej.tejkogames.api.services;

import java.util.ArrayList;
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
import matej.tejkogames.interfaces.api.services.RoleServiceInterface;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.RoleRequest;

@Service
public class RoleService implements RoleServiceInterface {

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
    public List<Role> getBulkById(Set<Integer> idSet) {
        return roleRepository.findAllById(idSet);
    }

    @Override
    public Role create(RoleRequest objectRequest) {
        Role role = new Role();
        if (objectRequest.getLabel() != null) {
            role.setLabel(objectRequest.getLabel());
        }
        if (objectRequest.getDescription() != null) {
            role.setDescription(objectRequest.getDescription());
        }
        return roleRepository.save(role);
    }

    @Override
    public List<Role> createBulk(List<RoleRequest> objectRequestList) {
        List<Role> roleList = new ArrayList<>();

        for (RoleRequest roleRequest : objectRequestList) {
            Role role = new Role();
            role.updateByRequest(roleRequest);
        }

        return roleRepository.saveAll(roleList);
    }

    @Override
    public Role updateById(Integer id, RoleRequest objectRequest) {
        Role role = getById(id);

        role.updateByRequest(objectRequest);

        return roleRepository.save(role);
    }

    @Override
    public List<Role> updateBulkById(Map<Integer, RoleRequest> idObjectRequestMap) {
        List<Role> roleList = getBulkById(idObjectRequestMap.keySet());

        for (Role role : roleList) {
            role.updateByRequest(idObjectRequestMap.get(role.getId()));
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
    public void deleteBulkById(Set<Integer> idSet) {
        roleRepository.deleteAllById(idSet);
    }

    public List<User> getUsersByRolesId(Integer id) {
        return userRepository.findAllByRolesId(id);
    }

}
