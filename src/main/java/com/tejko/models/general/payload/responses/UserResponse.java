package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.general.User;
import com.tejko.models.general.payload.RestResponse;

public class UserResponse extends RestResponse<User> {
    
    private String username;
    private List<RoleResponse> roles;
    private PreferenceResponse preference;
    private LocalDateTime createdDate;
    
    public UserResponse(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String username, List<RoleResponse> roles, PreferenceResponse preference) {
        super(id, createdDate, lastModifiedDate);
        this.username = username;
        this.roles = roles;
        this.preference = preference;
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public List<RoleResponse> getRoles() {
        return roles;
    }

    public PreferenceResponse getPreference() {
        return preference;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }    

}