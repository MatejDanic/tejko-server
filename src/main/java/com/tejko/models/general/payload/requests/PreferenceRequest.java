package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tejko.constants.TejkoConstants;
import com.tejko.models.general.Preference;
import com.tejko.models.general.enums.Theme;
import com.tejko.models.general.enums.VolumeLevel;
import com.tejko.models.general.payload.RestRequest;

public class PreferenceRequest extends RestRequest<Preference> {

    @NotBlank
    private UUID userId;

    @Size(min = TejkoConstants.VOLUME_MIN, max = TejkoConstants.VOLUME_MAX)
    private VolumeLevel volumeLevel;

    private Theme theme;

    public UUID getUserId() {
        return userId;
    }

    public VolumeLevel getVolumeLevel() {
        return volumeLevel;
    }

    public Theme getTheme() {
        return theme;
    }

}