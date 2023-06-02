package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.RoleRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.factories.RoleFactory;
import com.tejko.interfaces.api.services.RoleServiceInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.RoleRequest;

@Service
public class RoleService implements RoleServiceInterface {

    @Resource
    RoleFactory roleFactory;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).get();
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
        Role role = roleFactory.getObject(objectRequest);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> createBulk(List<RoleRequest> objectRequestList) {
        List<Role> roleList = new ArrayList<>();

        for (RoleRequest objectRequest : objectRequestList) {
            roleList.add(roleFactory.getObject(objectRequest));
        }

        return roleRepository.saveAll(roleList);
    }

    @Override
    public Role updateById(Integer id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        Role role = getById(id);

        role = applyPatch(role, objectPatch);

        return roleRepository.save(role);
    }

    @Override
    public List<Role> updateBulkById(Map<Integer, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<Role> roleList = getBulkById(idObjectPatchMap.keySet());

        for (Role role : roleList) {
            role = applyPatch(role, idObjectPatchMap.get(role.getId()));
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

    @Override
    public List<User> getUsersByRolesId(Integer id) {
        return userRepository.findAllByRolesId(id);
    }

    @Override
    public boolean hasPermission(UUID userId, Integer objectId) {
        return false;
    }

    @Override
    public Role applyPatch(Role object, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, Role.class);
    }

}
