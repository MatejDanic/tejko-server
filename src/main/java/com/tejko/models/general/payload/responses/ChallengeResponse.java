package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.general.Challenge;

public class ChallengeResponse extends ApiResponse<Challenge> {
    
    private UUID id;
    private int appId;
    private List<UserChallengeResponse> userChallengeList;

    public ChallengeResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, int appId, List<UserChallengeResponse> userChallengeList) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.appId = appId;
        this.userChallengeList = userChallengeList;
    }

    public UUID getId() {
        return id;
    }

    public int getAppId() {
        return appId;
    }

    public List<UserChallengeResponse> getUserChallengeList() {
        return userChallengeList;
    }
    
}