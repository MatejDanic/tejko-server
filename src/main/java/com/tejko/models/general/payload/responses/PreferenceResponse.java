package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Preference;
import com.tejko.models.general.enums.Theme;
import com.tejko.models.general.enums.VolumeLevel;

public class PreferenceResponse extends ApiResponse<Preference> {

    public UUID id;
    public VolumeLevel volumeLevel;
    public Theme theme;
	
	public PreferenceResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, VolumeLevel volumeLevel, Theme theme) {
		super(createdDate, lastModifiedDate);
		this.id = id;
		this.volumeLevel = volumeLevel;
		this.theme = theme;
	}

	public UUID getId() {
		return id;
	}

	public VolumeLevel getVolumeLevel() {
		return volumeLevel;
	}

	public Theme getTheme() {
		return theme;
	}
	
}