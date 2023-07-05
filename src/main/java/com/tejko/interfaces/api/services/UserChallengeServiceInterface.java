package com.tejko.interfaces.api.services;

import com.tejko.interfaces.api.RestService;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

public interface UserChallengeServiceInterface extends RestService<UserChallengeId, UserChallenge, UserChallengeRequest, UserChallengeResponse> {

}
