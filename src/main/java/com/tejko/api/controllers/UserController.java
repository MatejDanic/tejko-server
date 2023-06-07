package com.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.UserService;
import com.tejko.interfaces.api.controllers.UserControllerInterface;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerInterface {

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<UserResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(userService.getBulkById(idSet), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<UserResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(userService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest objectRequest) {
		return new ResponseEntity<>(userService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<UserResponse>> createBulk(@RequestBody List<UserRequest> objectRequestList) {
		return new ResponseEntity<>(userService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<UserResponse> updateById(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.updateById(id, userRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/bulk")
	@Override
	public ResponseEntity<List<UserResponse>> updateBulkById(@RequestBody Map<UUID, UserRequest> idUserRequestMap) {
		return new ResponseEntity<>(userService.updateBulkById(idUserRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<?> deleteBulkById(@RequestBody Set<UUID> idSet) {
		userService.deleteBulkById(idSet);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<?> deleteAll() {
		userService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or authentication.principal.username.equals(\"Matej\")")
	@PutMapping("/{id}/assign-role")
	@Override
	public ResponseEntity<UserResponse> assignRoleByUserId(@PathVariable UUID id, @RequestBody Integer roleId) {
		return new ResponseEntity<>(userService.assignRoleByUserId(id, roleId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/preference")
	@Override
	public ResponseEntity<PreferenceResponse> getPreferenceByUserId(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getPreferenceByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/scores")
	@Override
	public ResponseEntity<List<ScoreResponse>> getScoresByUserId(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getScoresByUserId(id), HttpStatus.OK);
	}

}
