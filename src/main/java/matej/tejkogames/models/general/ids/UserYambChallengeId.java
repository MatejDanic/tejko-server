package matej.tejkogames.models.general.ids;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class UserYambChallengeId implements Serializable {

    private UUID userId;

    private UUID challengeId;

    public UserYambChallengeId(UUID userId, UUID challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
    }

    public UserYambChallengeId() {
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
        UserYambChallengeId other = (UserYambChallengeId) obj;

        return this.challengeId == other.getChallengeId() && this.userId == other.getUserId();
    }

}