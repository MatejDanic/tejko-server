package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.App;

public class AppResponse extends ApiResponse<App> {
    
    private String name;
    private String description;
    
    public AppResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String name, String description) {
        super(id, createdDate, lastModifiedDate);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    

}