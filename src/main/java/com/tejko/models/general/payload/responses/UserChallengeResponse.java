package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.payload.RestResponse;

public class UserChallengeResponse extends RestResponse<UserChallenge> {
    
    private UUID userId;
    private UUID challengeId;
    private UUID gameId;

    public UserChallengeResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID userId, UUID challengeId, UUID gameId) {
        super(createdDate, lastModifiedDate);
        //super(id, createdDate, lastModifiedDate);
        this.userId = userId;
        this.challengeId = challengeId;
        this.gameId = gameId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public UUID getGameId() {
        return gameId;
    }   

}