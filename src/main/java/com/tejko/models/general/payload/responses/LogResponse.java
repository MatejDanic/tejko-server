package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Log;
import com.tejko.models.general.enums.LogLevel;

public class LogResponse extends ApiResponse<Log> {
    
    private UUID id;
    private UUID userId;
    private LogLevel level;
    private String content;

    public LogResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, UUID userId, LogLevel level, String content) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.userId = userId;
        this.level = level;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public LogLevel getLevel() {
        return level;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
    
}
