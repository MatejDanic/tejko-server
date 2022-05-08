package matej.tejkogames.models.general;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.interfaces.models.PreferenceInterface;
import matej.tejkogames.models.general.enums.Theme;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

@Entity
@Table(name = "user_preference")
@RestResource(rel = "preferences", path = "preferences")
public class Preference implements PreferenceInterface {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @OneToOne
    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private int volume;

    @Column()
    private Theme theme;

    public Preference() {
    }

    public Preference(int volume, Theme theme) {
        this.volume = volume;
        this.theme = theme;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void updateByRequest(PreferenceRequest preferenceRequest) {
        if (preferenceRequest.getTheme() != null) {
            this.setTheme(preferenceRequest.getTheme());
        }
        if (preferenceRequest.getVolume() != null) {
            this.setVolume(preferenceRequest.getVolume());
        }
    }

    @Override
    public boolean hasPermission(UUID userId) {
        return user.getId().equals(userId);
    }

}