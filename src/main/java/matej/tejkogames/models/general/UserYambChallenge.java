package matej.tejkogames.models.general;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import matej.tejkogames.models.general.enums.YambChallengeStatus;
import matej.tejkogames.models.general.ids.UserYambChallengeId;
import matej.tejkogames.models.general.payload.requests.UserYambChallengeRequest;

@Entity
@Table(name = "user_yamb_challenge")
public class UserYambChallenge {

    @EmbeddedId
    private UserYambChallengeId id;

    @JsonIncludeProperties({ "id" })
    @MapsId("userId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private User user;

    @JsonIncludeProperties({ "id" })
    @MapsId("challengeId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private YambChallenge challenge;

    @JsonIncludeProperties({ "id" })
    @JoinColumn(name = "yamb_id", referencedColumnName = "id")
    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST })
    private Yamb yamb;

    @Column
    private boolean accepted = false;

    @Column
    private YambChallengeStatus status = YambChallengeStatus.PENDING_RESPONSE;

    public UserYambChallenge(User user, YambChallenge challenge) {
        this.user = user;
        this.challenge = challenge;
    }

    public UserYambChallenge() {
    }

    public UserYambChallengeId getId() {
        return id;
    }

    public void setId(UserYambChallengeId id) {
        this.id = id;
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

    public Yamb getYamb() {
        return yamb;
    }

    public void setYamb(Yamb yamb) {
        this.yamb = yamb;
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

    public void updateByRequest(UserYambChallengeRequest objectRequest) {
        if (objectRequest.getUser() != null) {
            this.setUser(objectRequest.getUser());
        }
        if (objectRequest.getChallenge() != null) {
            this.setChallenge(objectRequest.getChallenge());
        }
        if (objectRequest.getYamb() != null) {
            this.setYamb(objectRequest.getYamb());
        }
        if (objectRequest.isAccepted() != null) {
            this.setAccepted(objectRequest.isAccepted());
        }
        if (objectRequest.getStatus() != null) {
            this.setStatus(objectRequest.getStatus());
        }
    }

}
