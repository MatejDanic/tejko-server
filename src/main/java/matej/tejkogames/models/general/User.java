package matej.tejkogames.models.general;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @Column(updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Score> scores;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Log> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Yamb> yambs;

    @Column(nullable = false, unique = true)
    @Size(min = TejkoGamesConstants.USERNAME_LENGTH_MIN, max = TejkoGamesConstants.USERNAME_LENGTH_MAX)
    private String username;

    @Column(unique = true)
    private String usernameLowercase;

    @PrePersist
    @PreUpdate
    private void prepare() {
        this.usernameLowercase = username == null ? null : username.toLowerCase();
    }

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIncludeProperties({ "id", "label" })
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
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