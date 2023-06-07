package com.tejko.api.controllers;

import java.time.LocalDateTime;
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

import com.tejko.api.services.ScoreService;
import com.tejko.interfaces.api.controllers.ScoreControllerInterface;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;

@RestController
@RequestMapping("/api/scores")
public class ScoreController implements ScoreControllerInterface {

	@Autowired
	ScoreService yambScoreService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<ScoreResponse> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(yambScoreService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<ScoreResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(yambScoreService.getBulkById(idSet), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<ScoreResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(yambScoreService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<ScoreResponse> create(@RequestBody ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<ScoreResponse>> createBulk(@RequestBody List<ScoreRequest> objectRequestList) {
		return new ResponseEntity<>(yambScoreService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/{id}}")
	@Override
	public ResponseEntity<ScoreResponse> updateById(@PathVariable UUID id, @RequestBody ScoreRequest scoreRequest) {
		return new ResponseEntity<>(yambScoreService.updateById(id, scoreRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/bulk")
	@Override
	public ResponseEntity<List<ScoreResponse>> updateBulkById(@RequestBody Map<UUID, ScoreRequest> idScoreRequestMap) {
		return new ResponseEntity<>(yambScoreService.updateBulkById(idScoreRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deleteById(@PathVariable UUID id) {
		yambScoreService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<?> deleteBulkById(@RequestBody Set<UUID> idSet) {
		yambScoreService.deleteBulkById(idSet);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<?> deleteAll() {
		yambScoreService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/interval")
	@Override
	public ResponseEntity<List<ScoreResponse>> getAllByDateInterval(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
		return new ResponseEntity<>(yambScoreService.getAllByDateInterval(startDate, endDate), HttpStatus.OK);
	}
}
