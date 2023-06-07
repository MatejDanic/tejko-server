package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Challenge;

public class ChallengeRequest extends ApiRequest<Challenge> {

    @NotBlank
    private Integer appId;

    public Integer getAppId() {
        return appId;
    }

}
