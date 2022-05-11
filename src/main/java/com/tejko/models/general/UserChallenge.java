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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import com.tejko.models.general.enums.ChallengeStatus;
import com.tejko.models.general.ids.UserChallengeId;

@Entity
@Table(name = "user_challenge")
public class UserChallenge {

    @JsonIgnore
    @EmbeddedId
    private UserChallengeId id;

    @JsonIncludeProperties({ "id" })
    @MapsId("userId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private User user;

    @JsonIncludeProperties({ "id" })
    @MapsId("challengeId")
    @ManyToOne(cascade = { CascadeType.REMOVE })
    private Challenge challenge;

    @JsonIncludeProperties({ "id" })
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
    private Game game;

    @Column
    private boolean accepted = false;

    @Column
    private ChallengeStatus status = ChallengeStatus.PENDING_RESPONSE;

    public UserChallenge() {
    }

    public UserChallengeId getId() {
        return id;
    }

    public void setId(UserChallengeId id) {
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
