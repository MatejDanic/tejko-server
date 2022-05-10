package matej.tejko.models.general.ids;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class UserChallengeId implements Serializable {

    private UUID userId;

    private UUID challengeId;

    public UserChallengeId(UUID userId, UUID challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
    }

    public UserChallengeId() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        UserChallengeId other = (UserChallengeId) obj;

        return this.challengeId == other.getChallengeId() && this.userId == other.getUserId();
    }

}