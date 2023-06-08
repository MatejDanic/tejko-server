package com.tejko.models.general;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.enums.ChallengeStatus;
import com.tejko.models.general.ids.UserChallengeId;

@Entity
@Table(name = "user_challenge")
public class UserChallenge extends DatabaseEntity {

    @EmbeddedId
    private UserChallengeId id;

    @MapsId("userId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private User user;

    @MapsId("challengeId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private Challenge challenge;

    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
    private Game game;

    @Column
    private boolean accepted = false;

    @Column
    private ChallengeStatus status = ChallengeStatus.PENDING_RESPONSE;

    private UserChallenge() { }

    private UserChallenge(UserChallengeId id, User user, Challenge challenge, Game game) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.game = game;
    }

    public static UserChallenge create(UserChallengeId id, User user, Challenge challenge, Game game) {
        return new UserChallenge(id, user, challenge, game);
    }

    public UserChallengeId getEmbeddedId() {
        return id;
    }

    public void setEmbeddedId(UserChallengeId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

}
