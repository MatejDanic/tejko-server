package com.tejko.models.general;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.tejko.models.DatabaseEntity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "challenge")
@RestResource(rel = "challenges", path = "challenges")
public class Challenge extends DatabaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

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

    public UUID getId() {
        return id;
    }

    public App getApp() {
        return app;
    }

    public Set<UserChallenge> getUserChallenges() {
        return userChallenges;
    }

}
