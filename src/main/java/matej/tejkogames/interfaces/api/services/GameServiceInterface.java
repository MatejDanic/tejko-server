package matej.tejkogames.interfaces.api.services;

import java.util.List;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.payload.requests.GameRequest;

public interface GameServiceInterface extends ServiceInterface<Game, Integer, GameRequest> {

    public List<Score> getScoresByGameId(Integer id);

}
