package matej.tejko.interfaces.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import matej.tejko.models.general.Score;
import matej.tejko.models.general.payload.requests.ScoreRequest;

public interface ScoreServiceInterface extends ServiceInterface<UUID, Score, ScoreRequest> {

    public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end);

}
