package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.responses.ScoreResponse;

public interface ScoreMapperInterface extends DatabaseEntityMapper<Score, ScoreResponse> {
    
}
