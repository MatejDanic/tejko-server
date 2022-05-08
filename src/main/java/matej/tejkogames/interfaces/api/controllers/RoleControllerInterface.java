package matej.tejkogames.interfaces.api.controllers;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.payload.requests.RoleRequest;

public interface RoleControllerInterface extends ControllerInterface<Role, Integer, RoleRequest> {

    public ResponseEntity<Role> updateById(Integer id, RoleRequest roleRequest);

}
