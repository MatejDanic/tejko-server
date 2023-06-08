package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Game;

public abstract class GameRequest extends ApiRequest<Game> {

    @NotBlank
    private UUID userId;

    @NotBlank
    private UUID appId;

    public UUID getUserId() {
        return userId;
    }

    public UUID getAppId() {
        return appId;
    }

}
