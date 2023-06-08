package com.tejko.models.general.payload.requests;

import java.util.UUID;

import com.tejko.models.general.Game;

public abstract class GameRequest extends ApiRequest<Game> {

    private UUID userId;

    private UUID appId;

    public GameRequest(UUID userId, UUID appId) {
        this.userId = userId;
        this.appId = appId;
    }

    public GameRequest(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getAppId() {
        return appId;
    }

}
