package matej.tejkogames.models.general.payload.requests;

import javax.validation.constraints.*;

import matej.tejkogames.constants.TejkoGamesConstants;

public class RegisterRequest {

    @NotBlank
    @Size(min = TejkoGamesConstants.USERNAME_LENGTH_MIN, max = TejkoGamesConstants.USERNAME_LENGTH_MAX)
    private String username;

    @NotBlank
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