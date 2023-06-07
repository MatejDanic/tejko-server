package com.tejko.interfaces.api.services;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

public interface UserChallengeServiceInterface extends ObjectServiceInterface<UserChallengeId, UserChallenge, UserChallengeRequest, UserChallengeResponse> {

}
