package matej.tejkogames.models.general.payload.requests;

public class UserRequest {

    private String username;

    private String password;

    private boolean isTestUser;

    public UserRequest() {
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

    public boolean isTestUser() {
        return isTestUser;
    }

    public void setTestUser(boolean isTestUser) {
        this.isTestUser = isTestUser;
    }

}
