package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.DatabaseEntity;

public abstract class ApiResponse<T extends DatabaseEntity> {

    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ApiResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ApiResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

}
