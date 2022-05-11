package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Boolean testUser = false;

    public UserRequest() {
    }

    public UserRequest(String username, String password, Boolean testUser) {
        this.username = username;
        this.password = password;
        this.testUser = testUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isTestUser() {
        return testUser;
    }

    public void setTestUser(Boolean testUser) {
        this.testUser = testUser;
    }

}
