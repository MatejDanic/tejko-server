package matej.tejkogames.models.general.payload.requests;

import java.time.LocalDateTime;

import matej.tejkogames.models.general.Game;
import matej.tejkogames.models.general.User;

public class ScoreRequest {

    private User user;

    private Game game;

    private Integer value;

    private LocalDateTime date;

    protected ScoreRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}