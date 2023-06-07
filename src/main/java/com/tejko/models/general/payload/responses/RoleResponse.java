package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;

import com.tejko.models.general.Role;

public class RoleResponse extends ApiResponse<Role> {
    
    public int id;
    public String label;
    public String description;

    public RoleResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, int id, String label, String description) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
    
}