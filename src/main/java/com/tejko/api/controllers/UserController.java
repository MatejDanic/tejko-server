package com.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.UserService;
import com.tejko.interfaces.api.controllers.UserControllerInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.Role;
import com.tejko.models.general.User;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerInterface {

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<User> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<User>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(userService.getBulkById(idSet), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<User>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(userService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<User> create(@RequestBody UserRequest objectRequest) {
		return new ResponseEntity<>(userService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<User>> createBulk(@RequestBody List<UserRequest> objectRequestList) {
		return new ResponseEntity<>(userService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<User> updateById(@PathVariable UUID id, @RequestBody JsonPatch objectPatch)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(userService.updateById(id, objectPatch), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/bulk")
	@Override
	public ResponseEntity<List<User>> updateBulkById(@RequestBody Map<UUID, JsonPatch> idObjectPatchMap)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(userService.updateBulkById(idObjectPatchMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
		userService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("User", "User has been successfully deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UUID> idSet) {
		userService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("User", "All users have been successfully deleted"),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		userService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("User", "All users have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or authentication.principal.username.equals(\"Matej\")")
	@PutMapping("/{id}/assign-role")
	@Override
	public ResponseEntity<Set<Role>> assignRoleByUserId(@PathVariable UUID id, @RequestBody Integer roleId) {
		return new ResponseEntity<>(userService.assignRoleByUserId(id, roleId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/preference")
	@Override
	public ResponseEntity<Preference> getPreferenceByUserId(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getPreferenceByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}/preference")
	@Override
	public ResponseEntity<String> deletePreferenceByUserId(@PathVariable UUID id) {
		userService.deletePreferenceByUserId(id);
		return new ResponseEntity<>("Preference for user with id " + id + " successfully deleted.", HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/scores")
	@Override
	public ResponseEntity<List<Score>> getScoresByUserId(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getScoresByUserId(id), HttpStatus.OK);
	}

}
