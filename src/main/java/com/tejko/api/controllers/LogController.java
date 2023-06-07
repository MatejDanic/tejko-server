package com.tejko.api.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.LogService;
import com.tejko.interfaces.api.controllers.LogControllerInterface;
import com.tejko.models.general.payload.responses.LogResponse;

@RestController
@RequestMapping("/api/logs")
public class LogController implements LogControllerInterface {

	@Autowired
	LogService logService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<LogResponse> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(logService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	@Override
	public ResponseEntity<List<LogResponse>> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "desc") String direction) {
		return new ResponseEntity<>(logService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<LogResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(logService.getBulkById(idSet), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		logService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<?> deleteAll() {
		logService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
