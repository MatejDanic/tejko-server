package com.tejko.models.general.payload.requests;

public class ChallengeRequest {

    private Integer appId;

    public ChallengeRequest() {
    }

    public ChallengeRequest(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

}
