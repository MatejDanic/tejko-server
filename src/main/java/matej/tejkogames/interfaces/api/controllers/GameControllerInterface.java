package matej.tejkogames.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.payload.requests.GameRequest;

public interface GameControllerInterface extends ControllerInterface<Game, Integer, GameRequest> {

    public ResponseEntity<Game> updateById(Integer id, GameRequest gameRequest);

    public ResponseEntity<List<Score>> getScoresByGameId(Integer id);

}
