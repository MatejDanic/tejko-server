package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Game;

public abstract class GameRequest extends ApiRequest<Game> {

    @NotBlank
    private UUID userId;

    @NotBlank
    private Integer appId;

    public UUID getUserId() {
        return userId;
    }

    public Integer getAppId() {
        return appId;
    }

}
