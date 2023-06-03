package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class ChallengeRequest {

    @NotBlank
    private Integer appId;

    public Integer getAppId() {
        return appId;
    }

}
