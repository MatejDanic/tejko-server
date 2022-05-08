package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.ScoreService;
import matej.tejkogames.interfaces.api.controllers.ScoreControllerInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.DateIntervalRequest;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/scores")
public class ScoreController implements ScoreControllerInterface {

	@Autowired
	ScoreService yambScoreService;

	@Override
	public ResponseEntity<Score> getById(@PathVariable UUID id) {
		return new ResponseEntity<>(yambScoreService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> getBulkById(@RequestBody Set<UUID> idSet) {
		return new ResponseEntity<>(yambScoreService.getBulkById(idSet), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Score>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String sort, @PathVariable String direction) {
		return new ResponseEntity<>(yambScoreService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Score> create(@RequestBody ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> createBulk(@RequestBody List<ScoreRequest> objectRequestList) {
		return new ResponseEntity<>(yambScoreService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Score> updateById(@PathVariable UUID id, @RequestBody ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> updateBulkById(@RequestBody Map<UUID, ScoreRequest> idObjectRequestMap) {
		return new ResponseEntity<>(yambScoreService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
		yambScoreService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Score", "Score has been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UUID> idSet) {
		yambScoreService.deleteBulkById(idSet);
		return new ResponseEntity<>(
				new MessageResponse("Score", "All scores have been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteAll() {
		yambScoreService.deleteAll();
		return new ResponseEntity<>(new MessageResponse("Score", "All scores have been successfully deleted."),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Score>> getAllByDateBetween(@RequestBody DateIntervalRequest dateIntervalRequest) {
		return new ResponseEntity<>(
				yambScoreService.getAllByDateBetween(dateIntervalRequest.getStart(), dateIntervalRequest.getEnd()),
				HttpStatus.OK);
	}

}
