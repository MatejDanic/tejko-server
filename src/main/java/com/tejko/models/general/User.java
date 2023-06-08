package com.tejko.models.general;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

import com.tejko.constants.TejkoConstants;
import com.tejko.models.DatabaseEntityWithId;

@Entity
@Table(name = "auth_user")
@RestResource(rel = "users", path = "users")
public class User extends DatabaseEntityWithId {

    @Column(nullable = false, unique = true)
    @Size(min = TejkoConstants.USERNAME_LENGTH_MIN, max = TejkoConstants.USERNAME_LENGTH_MAX)
    private String username;

    @Column(unique = true)
    private String usernameLowercase;

    @Column(nullable = false)
    private String password;

    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Preference preference;

    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @OneToMany(mappedBy = "user")
    private Set<Score> scores;

    @OneToMany(mappedBy = "user")
    private Set<Game> games;

    @OneToMany(mappedBy = "user")
    private Set<UserChallenge> userChallenges;

    @Column
    private boolean testUser = false;

    @PrePersist
    @PreUpdate
    private void prepare() {
        this.usernameLowercase = username == null ? null : username.toLowerCase();
    }

    private User() { }    

    private User(String username, String password, Set<Role> roles, boolean testUser) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.testUser = testUser;
    }

    public static User create(String username, String password, Set<Role> roles, boolean testUser) {
        return new User(username, password, roles, testUser);
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

    public void assignRole(Role role) {
        this.roles.add(role);
    }

}