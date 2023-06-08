package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Preference;
import com.tejko.models.general.enums.Theme;
import com.tejko.models.general.enums.VolumeLevel;

public class PreferenceResponse extends ApiResponse<Preference> {

    public VolumeLevel volumeLevel;
    public Theme theme;
	
	public PreferenceResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, VolumeLevel volumeLevel, Theme theme) {
		super(id, createdDate, lastModifiedDate);
		this.volumeLevel = volumeLevel;
		this.theme = theme;
	}

	public VolumeLevel getVolumeLevel() {
		return volumeLevel;
	}

	public Theme getTheme() {
		return theme;
	}
	
}