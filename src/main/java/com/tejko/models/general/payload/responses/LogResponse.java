package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Log;
import com.tejko.models.general.enums.LogLevel;
import com.tejko.models.general.payload.RestResponse;

public class LogResponse extends RestResponse<Log> {
    
    private UUID userId;
    private LogLevel level;
    private String content;

    public LogResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID userId, LogLevel level, String content) {
        super(id, createdDate, lastModifiedDate);
        this.userId = userId;
        this.level = level;
        this.content = content;
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
