package matej.tejkogames.models.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import matej.tejkogames.models.general.enums.YambChallengeStatus;
import matej.tejkogames.models.general.ids.UserYambChallengeId;

@Entity
@Table(name = "user_yamb_challenge")
@IdClass(UserYambChallengeId.class)
public class UserYambChallenge {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private YambChallenge challenge;

    @Column
    private boolean accepted;

    @Column
    private YambChallengeStatus status;

    public UserYambChallenge(User user, YambChallenge challenge) {
        this.user = user;
        this.challenge = challenge;
        this.accepted = false;
        this.status = YambChallengeStatus.PENDING_RESPONSE;
    }

    public UserYambChallenge() {
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public YambChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(YambChallengeStatus status) {
        this.status = status;
    }

}
