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
	@Override
	public ResponseEntity<Yamb> getById(UUID id) {
		return new ResponseEntity<>(yambService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Yamb>> getBulkById(Set<UUID> idSet) {
		return new ResponseEntity<>(yambService.getBulkById(idSet), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Yamb>> getAll(Integer page, Integer size, String sort, String direction) {
		return new ResponseEntity<>(yambService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Yamb> create(YambRequest objectRequest) {
		return new ResponseEntity<>(yambService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Yamb>> createBulk(List<YambRequest> objectRequestList) {
		return new ResponseEntity<>(yambService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Yamb> updateById(UUID id, YambRequest objectRequest) {
		return new ResponseEntity<>(yambService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Yamb>> updateBulkById(Map<UUID, YambRequest> idObjectRequestMap) {
		return new ResponseEntity<>(yambService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@Override
	public ResponseEntity<MessageResponse> deleteById(UUID id) {
		yambService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Yamb", "Yamb has been successfully deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
		yambService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		yambService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Yamb", "All yambs have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	public ResponseEntity<Set<Dice>> rollDiceById(UUID id) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.rollDiceById(id), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@Override
	public ResponseEntity<BoxType> announceById(UUID id, ColumnType columnType,
			BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.announceById(id, boxType), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@Override
	public ResponseEntity<Yamb> fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalMoveException {
		return new ResponseEntity<>(yambService.fillById(id, columnType, boxType), HttpStatus.OK);
	}

}
