package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.general.Challenge;

public class ChallengeResponse extends ApiResponse<Challenge> {
    
    private UUID appId;
    private List<UserChallengeResponse> userChallengeList;

    public ChallengeResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID appId, List<UserChallengeResponse> userChallengeList) {
        super(id, createdDate, lastModifiedDate);
        this.appId = appId;
        this.userChallengeList = userChallengeList;
    }

    public UUID getAppId() {
        return appId;
    }

    public List<UserChallengeResponse> getUserChallengeList() {
        return userChallengeList;
    }
    
}