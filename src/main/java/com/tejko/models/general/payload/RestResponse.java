package com.tejko.models.general.payload;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.DatabaseEntity;

public abstract class RestResponse<T extends DatabaseEntity> {

    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public RestResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public RestResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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
