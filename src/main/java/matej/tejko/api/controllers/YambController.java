package matej.tejko.api.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejko.api.services.UserService;
import matej.tejko.api.services.YambService;
import matej.tejko.exceptions.IllegalActionException;
import matej.tejko.interfaces.api.controllers.YambControllerInterface;
import matej.tejko.models.general.payload.requests.YambRequest;
import matej.tejko.models.general.payload.responses.MessageResponse;
import matej.tejko.models.yamb.Dice;
import matej.tejko.models.yamb.Yamb;
import matej.tejko.models.yamb.enums.BoxType;
import matej.tejko.models.yamb.enums.ColumnType;

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
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<Yamb>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(yambService.getBulkById(idSet), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	@Override
	public ResponseEntity<List<Yamb>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(yambService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("")
	@Override
	public ResponseEntity<Yamb> create(@RequestBody YambRequest objectRequest) {
		return new ResponseEntity<>(yambService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<Yamb>> createBulk(@RequestBody List<YambRequest> objectRequestList) {
		return new ResponseEntity<>(yambService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Yamb> updateById(@PathVariable UUID id, @RequestBody JsonPatch objectPatch)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(yambService.updateById(id, objectPatch), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/bulk")
	@Override
	public ResponseEntity<List<Yamb>> updateBulkById(@RequestBody Map<UUID, JsonPatch> idObjectPatchMap)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(yambService.updateBulkById(idObjectPatchMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
		yambService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Yamb", "Yamb has been successfully deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UUID> idSet) {
		yambService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		yambService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/roll")
	@Override
	public ResponseEntity<Set<Dice>> rollDiceById(@PathVariable UUID id) throws IllegalActionException {
		return new ResponseEntity<>(yambService.rollDiceById(id), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/announce")
	@Override
	public ResponseEntity<BoxType> announceById(@PathVariable UUID id, @PathVariable ColumnType columnType,
			@PathVariable BoxType boxType)
			throws IllegalActionException {
		return new ResponseEntity<>(yambService.announceById(id, boxType), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
	@Override
	public ResponseEntity<Yamb> fillById(@PathVariable UUID id, @PathVariable ColumnType columnType,
			@PathVariable BoxType boxType) throws IllegalActionException {
		return new ResponseEntity<>(yambService.fillById(id, columnType, boxType), HttpStatus.OK);
	}

}
