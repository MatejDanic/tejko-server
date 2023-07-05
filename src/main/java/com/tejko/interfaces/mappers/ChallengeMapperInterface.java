package com.tejko.interfaces.mappers;

import com.tejko.interfaces.DatabaseEntityMapper;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.responses.ChallengeResponse;

public interface ChallengeMapperInterface extends DatabaseEntityMapper<Challenge, ChallengeResponse> {
    
}
