package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.responses.GameResponse;
import com.tejko.models.general.Game;

public interface GameServiceInterface extends ObjectServiceInterface<UUID, Game, GameRequest, GameResponse> {

}
