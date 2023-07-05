package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Game;
import com.tejko.models.general.payload.RestResponse;

public abstract class GameResponse extends RestResponse<Game> {

    public GameResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        super(id, createdDate, lastModifiedDate);
    }

}