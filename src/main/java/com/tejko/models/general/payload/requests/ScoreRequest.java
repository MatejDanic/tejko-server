package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class ScoreRequest {

    @NotBlank
    private UUID userId;

    @NotBlank
    private Integer appId;

    @NotBlank
    private Integer value;

    public ScoreRequest(UUID userId, Integer appId, Integer value) {
        this.userId = userId;
        this.appId = appId;
        this.value = value;
    }

    public UUID getUserId() {
        return userId;
    }

    public Integer getAppId() {
        return appId;
    }

    public Integer getValue() {
        return value;
    }

}