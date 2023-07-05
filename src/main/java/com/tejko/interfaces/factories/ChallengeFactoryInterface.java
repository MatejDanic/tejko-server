package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;

public interface ChallengeFactoryInterface extends DatabaseEntityFactory<Challenge, ChallengeRequest> {

}
