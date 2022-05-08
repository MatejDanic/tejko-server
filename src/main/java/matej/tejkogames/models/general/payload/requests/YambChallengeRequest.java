package matej.tejkogames.models.general.payload.requests;

import java.util.Set;

import matej.tejkogames.models.general.UserYambChallenge;

public class YambChallengeRequest {

    private Set<UserYambChallenge> userYambChallenges;

    public YambChallengeRequest() {
    }

    public Set<UserYambChallenge> getUserYambChallenges() {
        return userYambChallenges;
    }

    public void setUserYambChallenges(Set<UserYambChallenge> userYambChallenges) {
        this.userYambChallenges = userYambChallenges;
    }

}
