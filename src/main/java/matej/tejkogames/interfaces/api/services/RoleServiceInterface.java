package matej.tejkogames.interfaces.api.services;

import java.util.List;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.RoleRequest;

public interface RoleServiceInterface extends ServiceInterface<Role, Integer, RoleRequest> {

    public List<User> getUsersByRolesId(Integer id);
}
