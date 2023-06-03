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

    public UUID getUserId() {
        return userId;
    }

    public Integer getVolume() {
        return volume;
    }

    public Theme getTheme() {
        return theme;
    }

}