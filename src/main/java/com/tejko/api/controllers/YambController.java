package com.tejko.api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.UserService;
import com.tejko.api.services.YambService;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.interfaces.api.controllers.YambControllerInterface;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

@RestController
@RequestMapping("/api/yambs")
public class YambController implements YambControllerInterface {

	@Autowired
	YambService yambService;

	@Autowired
	UserService userService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("")
	@Override
	public ResponseEntity<YambResponse> create(Principal principal) {
		return new ResponseEntity<>(yambService.create(principal), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/roll")
	@Override
	public ResponseEntity<YambResponse> rollDiceById(@PathVariable UUID id, @RequestBody List<Integer> diceToRoll) throws IllegalActionException {
		return new ResponseEntity<>(yambService.rollDiceById(id, diceToRoll), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/announce")
	@Override
	public ResponseEntity<YambResponse> announceById(@PathVariable UUID id, @RequestBody BoxType boxType) throws IllegalActionException {
		return new ResponseEntity<>(yambService.announceById(id, boxType), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/columns/{columnType}/boxes/{boxType}/fill")
	@Override
	public ResponseEntity<YambResponse> fillById(@PathVariable UUID id, @PathVariable ColumnType columnType, @PathVariable BoxType boxType) throws IllegalActionException {
		return new ResponseEntity<>(yambService.fillById(id, columnType, boxType), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambService.getById(#id))")
	@PutMapping("/{id}/restart")
	@Override
	public ResponseEntity<YambResponse> restartById(@PathVariable UUID id) throws IllegalActionException {
		return new ResponseEntity<>(yambService.restartById(id), HttpStatus.OK);
	}

}
