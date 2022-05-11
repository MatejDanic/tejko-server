package com.tejko.api.controllers;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.ScoreService;
import com.tejko.interfaces.api.controllers.ScoreControllerInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.DateIntervalRequest;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/scores")
public class ScoreController implements ScoreControllerInterface {

	@Autowired
	ScoreService yambScoreService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Score> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(yambScoreService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/bulk")
	@Override
	public ResponseEntity<List<Score>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(yambScoreService.getBulkById(idSet), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<Score>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(yambScoreService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	@Override
	public ResponseEntity<Score> create(@RequestBody ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/bulk")
	@Override
	public ResponseEntity<List<Score>> createBulk(@RequestBody List<ScoreRequest> objectRequestList) {
		return new ResponseEntity<>(yambScoreService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/{id}}")
	@Override
	public ResponseEntity<Score> updateById(@PathVariable UUID id, @RequestBody JsonPatch objectPatch)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(yambScoreService.updateById(id, objectPatch), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/bulk")
	@Override
	public ResponseEntity<List<Score>> updateBulkById(@RequestBody Map<UUID, JsonPatch> idObjectPatchMap)
			throws JsonProcessingException, JsonPatchException {
		return new ResponseEntity<>(yambScoreService.updateBulkById(idObjectPatchMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
		yambScoreService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Score", "Score has been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UUID> idSet) {
		yambScoreService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Score", "All scores have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		yambScoreService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Score", "All scores have been successfully deleted."),
				HttpStatus.OK);
	}

	@GetMapping("/interval")
	@Override
	public ResponseEntity<List<Score>> getAllByDateBetween(@RequestBody DateIntervalRequest dateIntervalRequest) {
		return new ResponseEntity<>(
				yambScoreService.getAllByDateBetween(dateIntervalRequest.getStart(), dateIntervalRequest.getEnd()),
				HttpStatus.OK);
	}

}
