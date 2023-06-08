package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Score;

public class ScoreRequest extends ApiRequest<Score> {

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID appId;

    @NotBlank
    private Integer value;

    public ScoreRequest(UUID userId, UUID appId, Integer value) {
        this.userId = userId;
        this.appId = appId;
        this.value = value;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getAppId() {
        return appId;
    }

    public Integer getValue() {
        return value;
    }

}