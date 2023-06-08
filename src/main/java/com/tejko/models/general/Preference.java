package com.tejko.models.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import org.springframework.data.rest.core.annotation.RestResource;

import com.tejko.models.DatabaseEntityWithId;
import com.tejko.models.general.enums.Theme;
import com.tejko.models.general.enums.VolumeLevel;

@Entity
@Table(name = "user_preference")
@RestResource(rel = "preferences", path = "preferences")
public class Preference extends DatabaseEntityWithId {

    @OneToOne
    @JsonIncludeProperties({ "id", "username" })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private VolumeLevel volumeLevel;

    @Column()
    private Theme theme;

    private Preference() { }

    private Preference(User user, VolumeLevel volumeLevel, Theme theme) {
        this.user = user;
        this.volumeLevel = volumeLevel;
        this.theme = theme;
    }

    public static Preference create(User user, VolumeLevel volumeLevel, Theme theme) {
        return new Preference(user, volumeLevel, theme);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VolumeLevel getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(VolumeLevel volumeLevel) {
        this.volumeLevel = volumeLevel;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

}