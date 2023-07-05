package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.RoleMapperInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.responses.RoleResponse;

@Component
public class RoleMapper implements RoleMapperInterface {

    @Override
    public RoleResponse toRestResponse(Role role) {
        return new RoleResponse(
            role.getId(), 
            role.getCreatedDate(), 
            role.getLastModifiedDate(), 
            role.getLabel(), 
            role.getDescription()
        );
    }

    @Override
    public List<RoleResponse> toRestResponseList(List<Role> roleList) {
        return roleList.stream().map(this::toRestResponse).collect(Collectors.toList());
    }

}
