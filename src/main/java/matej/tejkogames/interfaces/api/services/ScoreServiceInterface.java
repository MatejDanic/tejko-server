package matej.tejkogames.interfaces.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.ScoreRequest;

public interface ScoreServiceInterface extends ServiceInterface<Score, UUID, ScoreRequest> {

    public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end);

}
