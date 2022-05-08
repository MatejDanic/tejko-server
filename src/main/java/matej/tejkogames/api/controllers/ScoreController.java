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
	public ResponseEntity<Score> getById(UUID id) {
		return new ResponseEntity<>(yambScoreService.getById(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> getBulkById(Set<UUID> idSet) {
		return new ResponseEntity<>(yambScoreService.getBulkById(idSet), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Score>> getAll(Integer page, Integer size, String sort, String direction) {
		return new ResponseEntity<>(yambScoreService.getAll(page, size, sort, direction), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Score> create(ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.create(objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> createBulk(List<ScoreRequest> objectRequestList) {
		return new ResponseEntity<>(yambScoreService.createBulk(objectRequestList), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<Score> updateById(UUID id, ScoreRequest objectRequest) {
		return new ResponseEntity<>(yambScoreService.updateById(id, objectRequest), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<List<Score>> updateBulkById(Map<UUID, ScoreRequest> idObjectRequestMap) {
		return new ResponseEntity<>(yambScoreService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteById(UUID id) {
		yambScoreService.deleteById(id);
		return new ResponseEntity<>(new MessageResponse("Score", "Score has been successfully deleted."),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
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
	public ResponseEntity<List<Score>> getAllByDateBetween(DateIntervalRequest dateIntervalRequest) {
		return new ResponseEntity<>(
				yambScoreService.getAllByDateBetween(dateIntervalRequest.getStart(), dateIntervalRequest.getEnd()),
				HttpStatus.OK);
	}

}
