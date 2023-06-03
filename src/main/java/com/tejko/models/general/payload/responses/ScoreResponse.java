package com.tejko.models.general.payload.responses;

import com.tejko.models.general.Score;
import com.tejko.models.general.enums.ResponseStatus;

public class ScoreResponse extends ApiResponse<Score> {

    public ScoreResponse(ResponseStatus status, String message, Score object) {
        super(status, message, object);
    }

    public ScoreResponse(Score object) {
        super(object);
    }

}