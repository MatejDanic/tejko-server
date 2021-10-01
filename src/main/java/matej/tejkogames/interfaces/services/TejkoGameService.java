package matej.tejkogames.interfaces.services;

import java.util.List;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.TejkoGame;
import matej.tejkogames.models.general.payload.requests.TejkoGameRequest;

public interface TejkoGameService extends ServiceInterface<TejkoGame, Integer, TejkoGameRequest> {

    public List<Score> getScoresByGameId(Integer id);
    
}
