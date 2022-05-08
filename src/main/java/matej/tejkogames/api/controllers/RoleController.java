package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.RoleService;
import matej.tejkogames.interfaces.api.controllers.RoleControllerInterface;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.RoleRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/roles")
public class RoleController implements RoleControllerInterface {

    @Autowired
    RoleService roleService;

    @Override
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Role>> getBulkById(@RequestBody Set<Integer> idSet) {
        return new ResponseEntity<>(roleService.getBulkById(idSet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Role>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(roleService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Role> create(@RequestBody RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Role>> createBulk(@RequestBody List<RoleRequest> objectRequestList) {
        return new ResponseEntity<>(roleService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Role> updateById(@PathVariable Integer id, RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Role>> updateBulkById(@RequestBody Map<Integer, RoleRequest> idObjectRequestMap) {
        return new ResponseEntity<>(roleService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Role", "Role has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<Integer> idSet) {
        roleService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        roleService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getUsersByRolesId(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getUsersByRolesId(id), HttpStatus.OK);
    }

}
