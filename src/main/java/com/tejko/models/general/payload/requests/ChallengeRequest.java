package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.RestRequest;

public class ChallengeRequest extends RestRequest<Challenge> {

    @NotBlank
    private UUID appId;

    public UUID getAppId() {
        return appId;
    }

}
