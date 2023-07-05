package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.payload.requests.UserChallengeRequest;

public interface UserChallengeFactoryInterface extends DatabaseEntityFactory<UserChallenge, UserChallengeRequest> {

}
