package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.RoleRepository;
import com.tejko.factories.RoleFactory;
import com.tejko.interfaces.api.services.RoleServiceInterface;
import com.tejko.mappers.RoleMapper;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@Service
public class RoleService implements RoleServiceInterface {

    @Autowired
    RoleRepository roleRepository;

    @Resource
    RoleFactory roleFactory;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserService userService;

    @Override
    public RoleResponse getById(UUID id) {
        return roleMapper.toApiResponse(roleRepository.getById(id));
    }

    @Override
    public List<RoleResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return roleMapper.toApiResponseList(roleRepository.findAll(pageable).getContent());
    }

    @Override
    public List<RoleResponse> getBulkById(Set<UUID> idSet) {
        return roleMapper.toApiResponseList(roleRepository.findAllById(idSet));
    }

    @Override
    public RoleResponse create(RoleRequest objectRequest) {
        Role role = roleFactory.getObject(objectRequest);
        return roleMapper.toApiResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> createBulk(List<RoleRequest> objectRequestList) {
        List<Role> roleList = new ArrayList<>();

        for (RoleRequest objectRequest : objectRequestList) {
            roleList.add(roleFactory.getObject(objectRequest));
        }

        return roleMapper.toApiResponseList(roleRepository.saveAll(roleList));
    }

    @Override
    public RoleResponse updateById(UUID id, RoleRequest roleRequest) {
        Role role = roleRepository.getById(id);

        role = applyPatch(role, roleRequest);

        return roleMapper.toApiResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> updateBulkById(Map<UUID, RoleRequest> idRoleRequestMap) {
        List<Role> roleList = roleRepository.findAllById(idRoleRequestMap.keySet());

        for (Role role : roleList) {
            role = applyPatch(role, idRoleRequestMap.get(role.getId()));
        }

        return roleMapper.toApiResponseList(roleRepository.saveAll(roleList));
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        roleRepository.deleteAllById(idSet);
    }

    @Override
    public List<UserResponse> getUsersByRoleId(UUID roleId) {
        return userService.getUsersByRoleId(roleId);
    }

    @Override
    public Role getEntityById(UUID roleId) {
        return roleRepository.getById(roleId);
    }

    @Override
    public boolean hasPermission(UUID userId, UUID roleId) {
        return false;
    }

    @Override
    public Role applyPatch(Role role, RoleRequest roleRequest) {
        if (roleRequest.getDescription() != null) {
            role.setDescription(roleRequest.getDescription());
        }
        return role;
    }

}
