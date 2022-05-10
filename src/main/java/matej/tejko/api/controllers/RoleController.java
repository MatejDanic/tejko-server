package matej.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejko.api.services.RoleService;
import matej.tejko.interfaces.api.controllers.RoleControllerInterface;
import matej.tejko.models.general.Role;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.RoleRequest;
import matej.tejko.models.general.payload.responses.MessageResponse;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<Role>> getBulkById(@RequestBody Set<Integer> idSet) {
        return new ResponseEntity<>(roleService.getBulkById(idSet), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<Role>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(roleService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    @Override
    public ResponseEntity<Role> create(@RequestBody RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<Role>> createBulk(@RequestBody List<RoleRequest> objectRequestList) {
        return new ResponseEntity<>(roleService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Role> updateById(@PathVariable Integer id, JsonPatch objectRequest)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(roleService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<Role>> updateBulkById(@RequestBody Map<Integer, JsonPatch> idObjectRequestMap)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(roleService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Role", "Role has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<Integer> idSet) {
        roleService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        roleService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Role", "All roles have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/users")
    @Override
    public ResponseEntity<List<User>> getUsersByRolesId(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getUsersByRolesId(id), HttpStatus.OK);
    }

}
