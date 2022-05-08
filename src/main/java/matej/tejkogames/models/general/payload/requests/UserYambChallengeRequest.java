package matej.tejkogames.models.general.payload.requests;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.general.enums.YambChallengeStatus;

public class UserYambChallengeRequest {

    private User user;

    private YambChallenge challenge;

    private Boolean accepted;

    private YambChallengeStatus status;

    private Yamb yamb;

    public UserYambChallengeRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public YambChallenge getChallenge() {
        return challenge;
    }

    public void setChallenge(YambChallenge challenge) {
        this.challenge = challenge;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public YambChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(YambChallengeStatus status) {
        this.status = status;
    }

    public Yamb getYamb() {
        return yamb;
    }

    public void setYamb(Yamb yamb) {
        this.yamb = yamb;
    }

}
