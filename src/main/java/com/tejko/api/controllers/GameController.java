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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.GameService;
import com.tejko.interfaces.api.controllers.GameControllerInterface;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.responses.GameResponse;

@RestController
@RequestMapping("/api/games")
public class GameController implements GameControllerInterface {

    
	@Autowired
	GameService gameService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), gameService.getById(#id))")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<GameResponse> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<GameResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(gameService.getBulkById(idSet), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	@Override
	public ResponseEntity<List<GameResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(gameService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<GameResponse> create(@RequestBody GameRequest objectRequest) {
		return new ResponseEntity<>(gameService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<GameResponse>> createBulk(@RequestBody List<GameRequest> objectRequestList) {
		return new ResponseEntity<>(gameService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<GameResponse> updateById(@PathVariable UUID id, @RequestBody GameRequest gameRequest) {
		return new ResponseEntity<>(gameService.updateById(id, gameRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/bulk")
	@Override
	public ResponseEntity<List<GameResponse>> updateBulkById(@RequestBody Map<UUID, GameRequest> idGameRequestMap) {
		return new ResponseEntity<>(gameService.updateBulkById(idGameRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), gameService.getById(#id))")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		gameService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<?> deleteBulkById(@RequestBody Set<UUID> idSet) {
		gameService.deleteBulkById(idSet);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<?> deleteAll() {
		gameService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
}
