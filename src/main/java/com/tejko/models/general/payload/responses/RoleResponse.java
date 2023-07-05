package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tejko.models.general.Role;
import com.tejko.models.general.payload.RestResponse;

public class RoleResponse extends RestResponse<Role> {
    
    public String label;
    public String description;

    public RoleResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String label, String description) {
        super(id, createdDate, lastModifiedDate);
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
    
}