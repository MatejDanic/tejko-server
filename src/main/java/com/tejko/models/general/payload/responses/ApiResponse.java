package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;

import com.tejko.models.DatabaseEntity;

public abstract class ApiResponse<T extends DatabaseEntity> {

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ApiResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

}
