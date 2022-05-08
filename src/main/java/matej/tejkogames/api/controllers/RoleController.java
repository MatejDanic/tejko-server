package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<Role>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(roleService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Role> create(@RequestBody RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<Role>> createBulk(List<RoleRequest> objectRequestList) {
        return new ResponseEntity<>(roleService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Role> updateById(@PathVariable Integer id, @RequestBody RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bulk")
    @Override
    public ResponseEntity<List<Role>> updateBulkById(Map<Integer, RoleRequest> idObjectRequestMap) {
        return new ResponseEntity<>(roleService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            Integer id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Role", "Role has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        roleService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
            @RequestBody Set<Integer> idSet) {
        roleService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersByRolesId(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getUsersByRolesId(id), HttpStatus.OK);
    }

}
