package com.tejko.interfaces.api.services;

import java.util.List;

import com.tejko.models.general.Role;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.RoleRequest;

public interface RoleServiceInterface extends ServiceInterface<Integer, Role, RoleRequest> {

    public List<User> getUsersByRolesId(Integer id);
}
