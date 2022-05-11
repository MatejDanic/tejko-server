package com.tejko.interfaces.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;

public interface ScoreServiceInterface extends ServiceInterface<UUID, Score, ScoreRequest> {

    public List<Score> getAllByDateBetween(LocalDateTime start, LocalDateTime end);

}
