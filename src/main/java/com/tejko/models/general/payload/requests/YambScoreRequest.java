package com.tejko.models.general.payload.requests;

import java.util.UUID;

public class YambScoreRequest extends ScoreRequest {

    public YambScoreRequest(UUID userId, Integer value) {
        super(userId, value);
    }

}