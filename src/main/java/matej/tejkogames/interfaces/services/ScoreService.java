package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

public interface ScoreService extends ServiceInterface<Score, UUID> {

    public Score updateById(UUID id, ScoreRequest scoreRequest);

}
