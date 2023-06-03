package com.tejko.interfaces.api.services;

import java.util.List;

import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface RoleServiceInterface extends ServiceInterface<Integer, Role, RoleRequest, RoleResponse> {

    public List<UserResponse> getUsersByRoleId(Integer roleId);

}
