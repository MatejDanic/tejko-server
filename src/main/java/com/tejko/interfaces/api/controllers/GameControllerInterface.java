package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.Game;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.responses.GameResponse;

public interface GameControllerInterface extends ObjectControllerInterface<UUID, Game, GameRequest, GameResponse>{

}
