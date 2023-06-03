package com.tejko.models.general.payload.responses;

import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.enums.ResponseStatus;

public class UserChallengeResponse extends ApiResponse<UserChallenge> {

    public UserChallengeResponse(ResponseStatus status, String message, UserChallenge object) {
        super(status, message, object);
    }

    public UserChallengeResponse(UserChallenge object) {
        super(object);
    }

}