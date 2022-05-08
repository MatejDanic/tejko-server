package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.UserService;
import matej.tejkogames.interfaces.api.controllers.UserControllerInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.UserRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerInterface {

	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<User> getById(UUID id) {
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<User>> getBulkById(Set<UUID> idSet) {
		return new ResponseEntity<>(userService.getBulkById(idSet), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<User>> getAll(Integer page, Integer size, String sort, String direction) {
		return new ResponseEntity<>(userService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<User> create(UserRequest objectRequest) {
		return new ResponseEntity<>(userService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<User>> createBulk(List<UserRequest> objectRequestList) {
		return new ResponseEntity<>(userService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@Override
	public ResponseEntity<User> updateById(UUID id, UserRequest objectRequest) {
		return new ResponseEntity<>(userService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<User>> updateBulkById(Map<UUID, UserRequest> idObjectRequestMap) {
		return new ResponseEntity<>(userService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@Override
	public ResponseEntity<MessageResponse> deleteById(UUID id) {
		userService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("User", "User has been successfully deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
		userService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("User", "All users have been successfully deleted"),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		userService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("User", "All users have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@Override
	public ResponseEntity<Set<Yamb>> getYambsByUserId(UUID id) {
		return new ResponseEntity<>(userService.getYambsByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @jwtComponent.getUsernameFromHeader(#headerAuth).equals(\"Matej\")")
	@Override
	public ResponseEntity<Set<Role>> assignRoleByUserId(UUID id, Integer roleId) {
		return new ResponseEntity<>(userService.assignRoleByUserId(id, roleId), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@Override
	public ResponseEntity<Preference> getPreferenceByUserId(UUID id) {
		return new ResponseEntity<>(userService.getPreferenceByUserId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<String> deletePreferenceByUserId(UUID id) {
		userService.deletePreferenceByUserId(id);
		return new ResponseEntity<>("Preference for user with id " + id + " successfully deleted.", HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userService.getById(#id))")
	@Override
	public ResponseEntity<List<Score>> getScoresByUserId(UUID id) {
		return new ResponseEntity<>(userService.getScoresByUserId(id), HttpStatus.OK);
	}

}
