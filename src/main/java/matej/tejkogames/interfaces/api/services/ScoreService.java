package matej.tejkogames.interfaces.api.services;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

public interface ScoreService extends ServiceInterface<Score, UUID, ScoreRequest> {

}
