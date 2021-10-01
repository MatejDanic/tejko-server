package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejkogames.api.services.RoleServiceImpl;
import matej.tejkogames.interfaces.controllers.RoleController;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.RoleRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/roles")
public class RoleControllerImpl implements RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<Role>> getAll(
						@RequestParam(defaultValue = "0") Integer page, 
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "id") String sort,
                        @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(roleService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Role> create(@RequestBody RoleRequest requestBody) {
        return new ResponseEntity<>(roleService.create(requestBody), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Override
	public ResponseEntity<Role> updateById(@PathVariable Integer id, @RequestBody RoleRequest requestBody) {
		return new ResponseEntity<>(roleService.updateById(id, requestBody), HttpStatus.OK);
	}
	
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("")
    @Override
	public ResponseEntity<List<Role>> updateAll(Map<Integer, RoleRequest> idRequestMap) {
		return new ResponseEntity<>(roleService.updateAll(idRequestMap), HttpStatus.OK);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            Integer id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        roleService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersByRolesId(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getUsersByRolesId(id), HttpStatus.OK);
    }

}
