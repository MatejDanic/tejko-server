package com.tejko.models.general.payload.requests;

import java.util.UUID;

import com.tejko.models.general.UserChallenge;

public class UserChallengeRequest extends ApiRequest<UserChallenge> {

    private UUID userId;
    private UUID challengeId;
    private UUID gameId;

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
