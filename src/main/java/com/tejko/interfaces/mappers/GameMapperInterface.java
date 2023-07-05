package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.Game;
import com.tejko.models.general.payload.responses.GameResponse;

public interface GameMapperInterface extends DatabaseEntityMapper<Game, GameResponse> {
    
}
