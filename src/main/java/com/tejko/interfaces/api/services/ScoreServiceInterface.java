package com.tejko.interfaces.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;

public interface ScoreServiceInterface extends ObjectServiceInterface<UUID, Score, ScoreRequest, ScoreResponse> {

    public List<ScoreResponse> getAllByDateInterval(LocalDateTime startDate, LocalDateTime endDate);

    public List<ScoreResponse> getScoresByUserId(UUID userId);

    public List<ScoreResponse> getAllYambScores();

}
