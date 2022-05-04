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

import matej.tejkogames.api.services.ScoreServiceImpl;
import matej.tejkogames.interfaces.api.controllers.ScoreController;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.DateIntervalRequest;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/scores")
public class ScoreControllerImpl implements ScoreController {

	@Autowired
	ScoreServiceImpl yambScoreService;

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Score> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(yambScoreService.getById(id), HttpStatus.OK);
	}

	@GetMapping("")
	@Override
	public ResponseEntity<List<Score>> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "desc") String direction) {
		return new ResponseEntity<>(yambScoreService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/{id}")
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
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Score> updateById(@PathVariable UUID id, @RequestBody ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/bulk")
	@Override
	public ResponseEntity<List<Score>> updateBulkById(@RequestBody Map<UUID, ScoreRequest> idObjectRequestMap) {
		return new ResponseEntity<>(yambScoreService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
			@PathVariable UUID id) {
		yambScoreService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Score deleted successfully."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("")
	@Override
	public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
		yambScoreService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("All scores have been deleted."), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/bulk")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
			@RequestBody Set<UUID> idSet) {
		yambScoreService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Score", MessageType.DEFAULT, "All scores have been successfully deleted"),
				HttpStatus.OK);
	}

	@GetMapping("/interval")
	public ResponseEntity<List<Score>> getAllByDateBetween(@RequestBody DateIntervalRequest dateIntervalRequest) {
		return new ResponseEntity<>(
				yambScoreService.getAllByDateBetween(dateIntervalRequest.getStart(), dateIntervalRequest.getEnd()),
				HttpStatus.OK);
	}

}
