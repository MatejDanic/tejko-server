package matej.tejkogames.models.general.payload.requests;

import javax.validation.constraints.Size;

import matej.tejkogames.constants.TejkoGamesConstants;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.enums.Theme;

public class PreferenceRequest {

    private User user;

    @Size(min = TejkoGamesConstants.VOLUME_MIN, max = TejkoGamesConstants.VOLUME_MAX)
    private Integer volume;

    private Theme theme;

    protected PreferenceRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

}