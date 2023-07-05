package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;

public interface ScoreFactoryInterface extends DatabaseEntityFactory<Score, ScoreRequest> {

}
