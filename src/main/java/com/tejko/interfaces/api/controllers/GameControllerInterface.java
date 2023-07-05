package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.interfaces.api.RestController;
import com.tejko.models.general.Game;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.responses.GameResponse;

public interface GameControllerInterface extends RestController<UUID, Game, GameRequest, GameResponse>{

}
