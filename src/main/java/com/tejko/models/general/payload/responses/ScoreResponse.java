package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Score;

public class ScoreResponse extends ApiResponse<Score> {

	private UUID id;
	private int appId;
	private int value;

	public ScoreResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, int appId, int value) {
		super(createdDate, lastModifiedDate);
		this.id = id;
		this.appId = appId;
		this.value = value;
	}

	public UUID getId() {
		return id;
	}

	public int getAppId() {
		return appId;
	}

	public int getValue() {
		return value;
	}
    
}