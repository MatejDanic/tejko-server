package matej.tejko.models.general.payload.requests;

import java.util.UUID;

public class UserChallengeRequest {

    private UUID userId;

    private UUID challengeId;

    private UUID yambId;

    public UserChallengeRequest() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }

    public UUID getYambId() {
        return yambId;
    }

    public void setYambId(UUID yambId) {
        this.yambId = yambId;
    }

}
