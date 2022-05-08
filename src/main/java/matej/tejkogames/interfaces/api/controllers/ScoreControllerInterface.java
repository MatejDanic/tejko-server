package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

public interface ScoreControllerInterface extends ControllerInterface<Score, UUID, ScoreRequest> {

    public ResponseEntity<Score> updateById(UUID id, ScoreRequest scoreRequest);

}
