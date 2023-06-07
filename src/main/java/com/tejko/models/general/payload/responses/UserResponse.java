package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.general.User;

public class UserResponse extends ApiResponse<User> {
    
    private UUID id;
    private String username;
    private List<RoleResponse> roles;
    private PreferenceResponse preference;
    private LocalDateTime createdDate;
    
    public UserResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, String username, List<RoleResponse> roles, PreferenceResponse preference) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.preference = preference;
        this.createdDate = createdDate;
    }

    public UUID getId() {
        return id;
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