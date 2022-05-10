package matej.tejko.factories;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.AppRepository;
import matej.tejko.api.repositories.UserRepository;
import matej.tejko.interfaces.factories.ScoreFactoryInterface;
import matej.tejko.models.general.App;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.ScoreRequest;

@Component
public class ScoreFactory implements ScoreFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Override
    public Score create(ScoreRequest objectRequest) {

        Score score = new Score();

        User user = userRepository.getById(objectRequest.getUserId());
        score.setUser(user);
        App app = appRepository.getById(objectRequest.getAppId());
        score.setApp(app);
        score.setValue(objectRequest.getValue());
        score.setDate(LocalDateTime.now());

        return score;
    }

}
