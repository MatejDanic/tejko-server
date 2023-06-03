package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.UUID;

import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.DateIntervalRequest;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.responses.ScoreResponse;

public interface ScoreServiceInterface extends ServiceInterface<UUID, Score, ScoreRequest, ScoreResponse> {

    public List<ScoreResponse> getAllByDateInterval(DateIntervalRequest dateIntervalRequest);

    public List<ScoreResponse> getScoresByUserId(UUID userId);

}
