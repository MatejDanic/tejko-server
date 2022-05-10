package matej.tejko.interfaces.api.services;

import java.util.List;

import matej.tejko.models.general.Role;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.RoleRequest;

public interface RoleServiceInterface extends ServiceInterface<Integer, Role, RoleRequest> {

    public List<User> getUsersByRolesId(Integer id);
}
