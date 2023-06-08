package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Score;

public class ScoreResponse extends ApiResponse<Score> {

	private UUID appId;
	private int value;

	public ScoreResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID appId, int value) {
		super(id, createdDate, lastModifiedDate);
		this.appId = appId;
		this.value = value;
	}

	public UUID getAppId() {
		return appId;
	}

	public int getValue() {
		return value;
	}
    
}