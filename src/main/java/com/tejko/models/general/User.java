package com.tejko.models.general;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

import com.tejko.constants.TejkoConstants;

@Entity
@Table(name = "auth_user")
@RestResource(rel = "users", path = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @Column(nullable = false, unique = true)
    @Size(min = TejkoConstants.USERNAME_LENGTH_MIN, max = TejkoConstants.USERNAME_LENGTH_MAX)
    private String username;

    @Column(unique = true)
    private String usernameLowercase;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIncludeProperties({ "id", "label" })
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany
    private Set<Role> roles;

    @JsonIgnoreProperties({ "user" })
    @OneToOne(mappedBy = "user")
    private Preference preference;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Score> scores;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Game> games;

    @JsonIgnoreProperties({ "user" })
    @OneToMany(mappedBy = "user")
    private Set<UserChallenge> userChallenges;

    @JsonIgnore
    @Column
    private boolean testUser = false;

    @Column
    private LocalDateTime createdDate;

    @PrePersist
    @PreUpdate
    private void prepare() {
        this.usernameLowercase = username == null ? null : username.toLowerCase();
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameLowercase() {
        return usernameLowercase;
    }

    public void setUsernameLowercase(String usernameLowercase) {
        this.usernameLowercase = usernameLowercase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<UserChallenge> getUserChallenges() {
        return userChallenges;
    }

    public void setUserChallenges(Set<UserChallenge> userChallenges) {
        this.userChallenges = userChallenges;
    }

    public boolean isTestUser() {
        return testUser;
    }

    public void setTestUser(boolean testUser) {
        this.testUser = testUser;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void assignRole(Role role) {
        this.roles.add(role);
    }

}