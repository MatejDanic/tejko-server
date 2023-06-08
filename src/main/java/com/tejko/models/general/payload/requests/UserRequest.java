package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tejko.constants.TejkoConstants;
import com.tejko.models.general.User;

public class UserRequest extends ApiRequest<User> {

    @NotBlank
    @Size(min = TejkoConstants.USERNAME_LENGTH_MIN, max = TejkoConstants.USERNAME_LENGTH_MAX)
    private String username;

    @NotBlank
    @Size(min = TejkoConstants.PASSWORD_LENGTH_MIN)
    private String password;

    private boolean testUser;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTestUser() {
        return testUser;
    }

}
