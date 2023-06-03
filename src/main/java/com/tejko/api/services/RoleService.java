package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@Service
public class RoleService implements RoleServiceInterface {

    @Resource
    RoleFactory roleFactory;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Override
    public RoleResponse getById(Integer id) {
        return toApiResponse(roleRepository.getById(id));
    }

    @Override
    public List<RoleResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(roleRepository.findAll(pageable).getContent());
    }

    @Override
    public List<RoleResponse> getBulkById(Set<Integer> idSet) {
        return toApiResponseList(roleRepository.findAllById(idSet));
    }

    @Override
    public RoleResponse create(RoleRequest objectRequest) {
        Role role = roleFactory.getObject(objectRequest);
        return toApiResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> createBulk(List<RoleRequest> objectRequestList) {
        List<Role> roleList = new ArrayList<>();

        for (RoleRequest objectRequest : objectRequestList) {
            roleList.add(roleFactory.getObject(objectRequest));
        }

        return toApiResponseList(roleRepository.saveAll(roleList));
    }

    @Override
    public RoleResponse updateById(Integer id, RoleRequest roleRequest) {
        Role role = roleRepository.getById(id);

        role = applyPatch(role, roleRequest);

        return toApiResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> updateBulkById(Map<Integer, RoleRequest> idRoleRequestMap) {
        List<Role> roleList = roleRepository.findAllById(idRoleRequestMap.keySet());

        for (Role role : roleList) {
            role = applyPatch(role, idRoleRequestMap.get(role.getId()));
        }

        return toApiResponseList(roleRepository.saveAll(roleList));
    }

    @Override
    public ApiResponse<?> deleteById(Integer id) {
        roleRepository.deleteById(id);
        return new ApiResponse<>("Role has been deleted successfully.");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        roleRepository.deleteAll();
        return new ApiResponse<>("Roles have been deleted successfully.");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<Integer> idSet) {
        roleRepository.deleteAllById(idSet);
        return new ApiResponse<>("All Roles have been deleted successfully.");
    }

    @Override
    public List<UserResponse> getUsersByRoleId(Integer roleId) {
        return userService.findAllByRolesId(roleId);
    }

    @Override
    public boolean hasPermission(UUID userId, Integer objectId) {
        return false;
    }

    @Override
    public Role applyPatch(Role role, RoleRequest roleRequest) {
        if (roleRequest.getLabel() != null) {
            role.setLabel(roleRequest.getLabel());
        }
        if (roleRequest.getDescription() != null) {
            role.setDescription(roleRequest.getDescription());
        }
        return role;
    }

    @Override
    public RoleResponse toApiResponse(Role object) {
        return new RoleResponse(object);
    }

    @Override
    public List<RoleResponse> toApiResponseList(List<Role> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
