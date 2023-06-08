package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.UUID;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface RoleServiceInterface extends ObjectServiceInterface<UUID, Role, RoleRequest, RoleResponse> {

    public List<UserResponse> getUsersByRoleId(UUID roleId);

    // this method is called by the user service for assigning new roles to users
    // that is why it returns Role instead of RoleResponse
    public Role getEntityByLabel(String label); 


}
