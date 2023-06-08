package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Challenge;

public class ChallengeRequest extends ApiRequest<Challenge> {

    @NotBlank
    private UUID appId;

    public UUID getAppId() {
        return appId;
    }

}
