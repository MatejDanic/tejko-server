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
import matej.tejkogames.api.services.YambServiceImpl;
import matej.tejkogames.exceptions.IllegalMoveException;
import matej.tejkogames.interfaces.controllers.YambController;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;
import matej.tejkogames.models.yamb.BoxType;
import matej.tejkogames.models.yamb.ColumnType;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.Yamb;
import matej.tejkogames.utils.JwtUtil;

@RestController
@RequestMapping("/api/yambs")
public class YambControllerImpl implements YambController {

	private static final String resource = "Yamb";

	@Autowired
	YambServiceImpl yambService;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	JwtUtil jwtUtil;

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtUtil.getUsernameFromHeader(#headerAuth), #id, 'Yamb')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Yamb> getById(@PathVariable(value = "id") UUID id) {
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
	public ResponseEntity<Yamb> create(@RequestBody YambRequest requestBody) {
		return new ResponseEntity<>(yambService.create(requestBody), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Yamb> updateById(@PathVariable UUID id, @RequestBody YambRequest requestBody) {
		return new ResponseEntity<>(yambService.updateById(id, requestBody), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("")
	@Override
	public ResponseEntity<List<Yamb>> updateAll(Map<UUID, YambRequest> idRequestMap) {
		return new ResponseEntity<>(yambService.updateAll(idRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtUtil.getUsernameFromHeader(#headerAuth), #id, 'Yamb')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable(value = "id") UUID id) {
		yambService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Yamb s id-em " + id + " uspješno izbrisan."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
		yambService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Svi Yambovi uspješno izbrisani."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteAllById(@RequestHeader(value = "Authorization") String headerAuth,
			@RequestBody Set<UUID> idSet) {
		yambService.deleteAllById(idSet);
		return new ResponseEntity<>(
				new MessageResponse(resource, MessageType.DEFAULT, "All yambs have been successfully deleted"),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtUtil.getUsernameFromHeader(#headerAuth), #id, 'Yamb')")
	@PutMapping("/{id}/roll")
	public ResponseEntity<Set<Dice>> rollDiceById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable(value = "id") UUID id) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.rollDiceById(id), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtUtil.getUsernameFromHeader(#headerAuth), #id, 'Yamb')")
	@PutMapping("/{id}/announce")
	public ResponseEntity<BoxType> announceById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable(value = "id") UUID id, @RequestBody BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.announceById(id, boxType), HttpStatus.OK);

	}

	@PutMapping("/{id}/columns/{columnTypeId}/boxes/{boxTypeId}/fill")
	public ResponseEntity<Yamb> fillById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable(value = "id") UUID id, @PathVariable(value = "columnType") ColumnType columnType,
			@PathVariable(value = "boxType") BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.fillById(id, columnType, boxType), HttpStatus.OK);

	}

}
