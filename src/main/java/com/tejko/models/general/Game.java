package com.tejko.models.general;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntity;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Game extends DatabaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @JsonIncludeProperties({ "id", "name" })
    @JoinColumn(name = "app_id", nullable = false)
    @ManyToOne
    private App app;

    @JsonIncludeProperties({ "user", "challenge" })
    @OneToOne(mappedBy = "game")
    private UserChallenge userChallenges;

    public Game() { }

    public Game(App app, User user) {
        this.app = app;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public UserChallenge getUserChallenges() {
        return userChallenges;
    }

    public void setUserChallenges(UserChallenge userChallenges) {
        this.userChallenges = userChallenges;
    }

}
