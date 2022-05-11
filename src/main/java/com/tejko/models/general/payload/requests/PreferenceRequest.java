package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tejko.constants.TejkoConstants;
import com.tejko.models.general.enums.Theme;

public class PreferenceRequest {

    @NotBlank
    private UUID userId;

    @Size(min = TejkoConstants.VOLUME_MIN, max = TejkoConstants.VOLUME_MAX)
    private Integer volume;

    private Theme theme;

    protected PreferenceRequest() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

}