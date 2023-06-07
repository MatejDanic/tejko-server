package com.tejko.models.general.payload.responses;

import java.util.Set;
import java.util.UUID;

public class LoginResponse {

    private String token;
    private UUID id;
    private String username;
    private Set<String> roles;
    
    public LoginResponse(String token, UUID id, String username, Set<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

}