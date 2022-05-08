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

import matej.tejkogames.api.services.UserService;
import matej.tejkogames.api.services.YambService;
import matej.tejkogames.exceptions.IllegalMoveException;
import matej.tejkogames.interfaces.api.controllers.YambControllerInterface;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.ColumnType;

@RestController
@RequestMapping("/api/yambs")
public class YambController implements YambControllerInterface {

	@Autowired
	YambService yambService;

	@Autowired
	UserService userService;

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Yamb> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(yambService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	@Override
	public ResponseEntity<List<Yamb>> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "desc") String direction) {
		return new ResponseEntity<>(yambService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<Yamb> create(@RequestBody YambRequest objectRequest) {
		return new ResponseEntity<>(yambService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<Yamb>> createBulk(List<YambRequest> objectRequestList) {
		return new ResponseEntity<>(yambService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Yamb> updateById(@PathVariable UUID id, @RequestBody YambRequest objectRequest) {
		return new ResponseEntity<>(yambService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/bulk")
	@Override
	public ResponseEntity<List<Yamb>> updateBulkById(Map<UUID, YambRequest> idObjectRequestMap) {
		return new ResponseEntity<>(yambService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		yambService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Yamb", "Yamb has been successfully deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
		yambService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
			@RequestBody Set<UUID> idSet) {
		yambService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/roll")
	public ResponseEntity<Set<Dice>> rollDiceById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.rollDiceById(id), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/announce")
	public ResponseEntity<BoxType> announceById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id, @RequestBody BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.announceById(id, boxType), HttpStatus.OK);

	}

	@PutMapping("/{id}/columns/{columnTypeId}/boxes/{boxTypeId}/fill")
	public ResponseEntity<Yamb> fillById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id, @PathVariable ColumnType columnType,
			@PathVariable BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.fillById(id, columnType, boxType), HttpStatus.OK);

	}

}