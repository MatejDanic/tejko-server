package matej.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.payload.requests.DateIntervalRequest;
import matej.tejko.models.general.payload.requests.ScoreRequest;

public interface ScoreControllerInterface extends ControllerInterface<UUID, Score, ScoreRequest> {

    @GetMapping("/interval")
    public ResponseEntity<List<Score>> getAllByDateBetween(@RequestBody DateIntervalRequest dateIntervalRequest);

}
