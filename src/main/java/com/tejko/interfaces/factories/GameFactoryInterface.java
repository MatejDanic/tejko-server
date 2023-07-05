package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Game;
import com.tejko.models.general.payload.requests.GameRequest;

public interface GameFactoryInterface extends DatabaseEntityFactory<Game, GameRequest> {

}
