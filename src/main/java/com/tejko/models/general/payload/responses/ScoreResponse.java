package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Score;

public class ScoreResponse extends ApiResponse<Score> {

	private UUID userId;
	private int value;

	public ScoreResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID userId, int value) {
		super(id, createdDate, lastModifiedDate);
		this.userId = userId;
		this.value = value;
	}

	public UUID getUserId() {
		return userId;
	}

	public int getValue() {
		return value;
	}

}