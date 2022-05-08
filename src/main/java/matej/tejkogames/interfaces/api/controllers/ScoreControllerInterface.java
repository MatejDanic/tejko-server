package matej.tejkogames.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.DateIntervalRequest;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

public interface ScoreControllerInterface extends ControllerInterface<Score, UUID, ScoreRequest> {

    @GetMapping("/interval")
    public ResponseEntity<List<Score>> getAllByDateBetween(@RequestBody DateIntervalRequest dateIntervalRequest);

}
