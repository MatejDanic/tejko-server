package com.tejko.models.general.payload.responses;

import com.tejko.models.general.Challenge;
import com.tejko.models.general.enums.ResponseStatus;

public class ChallengeResponse extends ApiResponse<Challenge> {

    public ChallengeResponse(ResponseStatus status, String message, Challenge object) {
        super(status, message, object);
    }

    public ChallengeResponse(Challenge object) {
        super(object);
    }

}