package com.tejko.models.general;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntityWithId;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "challenge")
@RestResource(rel = "challenges", path = "challenges")
public class Challenge extends DatabaseEntityWithId {

    @JsonIncludeProperties({ "id", "name" })
    @JoinColumn(name = "app_id", nullable = false)
    @ManyToOne
    private App app;

    @JsonIncludeProperties({ "user" })
    @OneToMany(mappedBy = "challenge")
    private Set<UserChallenge> userChallenges;

    private Challenge() { }

    private Challenge(App app) {
        this.app = app;
    }

    public static Challenge create(App app) {
        return new Challenge(app);
    }

    public App getApp() {
        return app;
    }

    public Set<UserChallenge> getUserChallenges() {
        return userChallenges;
    }

}
