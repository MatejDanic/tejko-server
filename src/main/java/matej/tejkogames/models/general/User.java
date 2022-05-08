package matej.tejkogames.models.general;

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

import matej.tejkogames.constants.TejkoGamesConstants;
import matej.tejkogames.interfaces.models.UserInterface;
import matej.tejkogames.models.general.payload.requests.UserRequest;

@Entity
@Table(name = "auth_user")
@RestResource(rel = "users", path = "users")
public class User implements UserInterface {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Score> scores;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Yamb> yambs;

    @Column(nullable = false, unique = true)
    @Size(min = TejkoGamesConstants.USERNAME_LENGTH_MIN, max = TejkoGamesConstants.USERNAME_LENGTH_MAX)
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
    @OneToMany(mappedBy = "user")
    private Set<UserYambChallenge> userYambChallenges;

    @JsonIgnoreProperties({ "user" })
    @OneToOne(mappedBy = "user")
    private Preference preference;

    @JsonIgnore
    @Column
    private Boolean isTestUser = false;

    @Column
    private LocalDateTime createdDate;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @PrePersist
    @PreUpdate
    private void prepare() {
        this.usernameLowercase = username == null ? null : username.toLowerCase();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Yamb> getYambs() {
        return yambs;
    }

    public void setYambs(Set<Yamb> yambs) {
        this.yambs = yambs;
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

    public Set<UserYambChallenge> getUserYambChallenges() {
        return userYambChallenges;
    }

    public void setUserYambChallenges(Set<UserYambChallenge> userYambChallenges) {
        this.userYambChallenges = userYambChallenges;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public Boolean isTestUser() {
        return isTestUser;
    }

    public void setTestUser(Boolean isTestUser) {
        this.isTestUser = isTestUser;
    }

    public Boolean getIsTestUser() {
        return isTestUser;
    }

    public void setIsTestUser(Boolean isTestUser) {
        this.isTestUser = isTestUser;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public void updateByRequest(UserRequest objectRequest) {
        if (objectRequest.getUsername() != null) {
            this.setUsername(objectRequest.getUsername());
        }
        if (objectRequest.getPassword() != null) {
            this.setPassword(objectRequest.getPassword());
        }
        if (objectRequest.isTestUser() != null) {
            this.setTestUser(objectRequest.isTestUser());
        }
    }

    public void assignRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public boolean hasPermission(UUID userId) {
        return id.equals(userId);
    }
}