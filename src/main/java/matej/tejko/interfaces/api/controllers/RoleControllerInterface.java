package matej.tejko.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import matej.tejko.models.general.Role;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.RoleRequest;

public interface RoleControllerInterface extends ControllerInterface<Integer, Role, RoleRequest> {

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersByRolesId(@PathVariable Integer id);

}
