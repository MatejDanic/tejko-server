package matej.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.AppRepository;
import matej.tejko.interfaces.factories.ChallengeFactoryInterface;
import matej.tejko.models.general.Challenge;
import matej.tejko.models.general.App;
import matej.tejko.models.general.payload.requests.ChallengeRequest;

@Component
public class ChallengeFactory implements ChallengeFactoryInterface {

    @Autowired
    AppRepository appRepository;

    @Override
    public Challenge create(ChallengeRequest objectRequest) {

        Challenge challenge = new Challenge();

        App app = appRepository.getById(objectRequest.getAppId());

        challenge.setApp(app);

        return challenge;
    }

}
