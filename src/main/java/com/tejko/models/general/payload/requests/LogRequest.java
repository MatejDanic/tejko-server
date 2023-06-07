package com.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Log;
import com.tejko.models.general.enums.LogLevel;

public class LogRequest extends ApiRequest<Log> {

    @NotBlank
    private UUID userId;

    @NotBlank
    private LogLevel level;

    @NotBlank
    private String content;

    public LogRequest(UUID userId, LogLevel level, String content) {
        this.userId = userId;
        this.level = level;
        this.content = content;
    }

    public UUID getUserId() {
        return userId;
    }

    public LogLevel getLevel() {
        return level;
    }
    
    public String getContent() {
        return content;
    }

}