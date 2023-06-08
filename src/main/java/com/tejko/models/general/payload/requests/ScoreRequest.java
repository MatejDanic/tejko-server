package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Score;

public abstract class ScoreRequest extends ApiRequest<Score> {

    @NotBlank
    private UUID userId;

    @NotBlank
    private Integer value;

    public ScoreRequest(UUID userId, Integer value) {
        this.userId = userId;
        this.value = value;
    }

    public UUID getUserId() {
        return userId;
    }

    public Integer getValue() {
        return value;
    }

}