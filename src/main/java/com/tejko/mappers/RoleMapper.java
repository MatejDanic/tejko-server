package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tejko.interfaces.mappers.RoleMapperInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.responses.RoleResponse;

public class RoleMapper implements RoleMapperInterface {

    @Override
    public RoleResponse toApiResponse(Role role) {
        return new RoleResponse(
            role.getCreatedDate(), 
            role.getLastModifiedDate(), 
            role.getId(), 
            role.getLabel(), 
            role.getDescription()
        );
    }

    @Override
    public List<RoleResponse> toApiResponseList(List<Role> roleList) {
        return roleList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
