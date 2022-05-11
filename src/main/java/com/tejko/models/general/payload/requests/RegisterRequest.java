package com.tejko.models.general.payload.requests;

import javax.validation.constraints.*;

import com.tejko.constants.TejkoConstants;

public class RegisterRequest {

    @NotBlank
    @Size(min = TejkoConstants.USERNAME_LENGTH_MIN, max = TejkoConstants.USERNAME_LENGTH_MAX)
    private String username;

    @NotBlank
    @Size(min = TejkoConstants.PASSWORD_LENGTH_MIN)
    private String password;

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

}