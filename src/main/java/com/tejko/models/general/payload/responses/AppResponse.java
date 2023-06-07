package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;

import com.tejko.models.general.App;

public class AppResponse extends ApiResponse<App> {
    
    private int id;
    private String name;
    private String description;
    
    public AppResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, int id, String name, String description) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    

}