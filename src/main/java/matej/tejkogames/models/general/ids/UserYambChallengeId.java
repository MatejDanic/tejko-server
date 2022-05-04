package matej.tejkogames.models.general.ids;

import java.io.Serializable;
import java.util.Objects;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.YambChallenge;

public class UserYambChallengeId implements Serializable {

    private User user;

    private YambChallenge challenge;

    public UserYambChallengeId(User user, YambChallenge challenge) {
        this.user = user;
        this.challenge = challenge;
    }

    public UserYambChallengeId() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public YambChallenge getYambChallenge() {
        return challenge;
    }

    public void setYambChallenge(YambChallenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, challenge);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        UserYambChallengeId other = (UserYambChallengeId) obj;

        return this.challenge == other.getYambChallenge() && this.user == other.user;
    }

}