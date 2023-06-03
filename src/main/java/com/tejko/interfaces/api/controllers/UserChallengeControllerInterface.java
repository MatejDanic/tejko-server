package com.tejko.interfaces.api.controllers;

import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

public interface UserChallengeControllerInterface extends ControllerInterface<UserChallengeId, UserChallenge, UserChallengeRequest, UserChallengeResponse> {

}
