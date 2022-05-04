package matej.tejkogames.factories;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.User;

@Component
public class ScoreFactory {

    public Score createScore(User user, Integer value) {
        Score score = new Score();
        score.setUser(user);
        score.setValue(value);
        score.setDate(LocalDateTime.now());
        return score;
    }

}
