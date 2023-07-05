package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;

public interface RoleFactoryInterface extends DatabaseEntityFactory<Role, RoleRequest> {

}
