package matej.tejkogames.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.payload.requests.GameRequest;

public interface GameControllerInterface extends ControllerInterface<Game, Integer, GameRequest> {

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<Score>> getScoresByGameId(@PathVariable Integer id);

}
