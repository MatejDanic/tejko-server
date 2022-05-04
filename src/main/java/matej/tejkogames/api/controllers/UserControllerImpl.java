package matej.tejkogames.api.controllers;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.UserServiceImpl;
import matej.tejkogames.interfaces.api.controllers.UserController;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.UserRequest;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<User> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<User>> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "desc") String direction) {
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
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<User> updateById(@PathVariable UUID id, @RequestBody UserRequest objectRequest) {
		return new ResponseEntity<>(userService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("")
	@Override
	public ResponseEntity<List<User>> updateBulkById(@RequestBody Map<UUID, UserRequest> idObjectRequestMap) {
		return new ResponseEntity<>(userService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		userService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("User deleted successfully."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
		userService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Svi korisnici uspješno izbrisani."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
			@RequestBody Set<UUID> idSet) {
		userService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("User", MessageType.DEFAULT, "All users have been successfully deleted"),
				HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/{id}/yambs")
	public ResponseEntity<Yamb> createYambByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id, @RequestBody YambRequest yambRequest) {
		return new ResponseEntity<>(userService.createYambByUserId(id, yambRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/yambs")
	public ResponseEntity<Set<Yamb>> getYambsByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getYambsByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), @userServiceImpl.getById(#id))")
	@PutMapping("/{id}/assign-role")
	public ResponseEntity<Set<Role>> assignRoleByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id, @RequestBody Integer roleId) {
		return new ResponseEntity<>(userService.assignRoleByUserId(id, roleId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/preferences")
	public ResponseEntity<Preference> getPreferenceByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getPreferenceByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}/preferences")
	public ResponseEntity<String> deletePreferenceByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		userService.deletePreferenceByUserId(id);
		return new ResponseEntity<>("Preference for user with id " + id + " successfully deleted.", HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@GetMapping("/{id}/scores")
	public ResponseEntity<List<Score>> getScoresByUserId(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getScoresByUserId(id), HttpStatus.OK);
	}

}
