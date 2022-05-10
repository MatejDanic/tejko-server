package matej.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.ChallengeRepository;
import matej.tejko.api.repositories.GameRepository;
import matej.tejko.api.repositories.UserRepository;
import matej.tejko.interfaces.factories.UserChallengeFactoryInterface;
import matej.tejko.models.general.Challenge;
import matej.tejko.models.general.User;
import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;
import matej.tejko.models.general.payload.requests.UserChallengeRequest;

@Component
public class UserChallengeFactory implements UserChallengeFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public UserChallenge create(UserChallengeRequest objectRequest) {

        UserChallenge userChallenge = new UserChallenge();

        userChallenge.setId(new UserChallengeId(objectRequest.getUserId(), objectRequest.getChallengeId()));

        User user = userRepository.getById(objectRequest.getUserId());
        userChallenge.setUser(user);

        Challenge challenge = challengeRepository.getById(objectRequest.getChallengeId());
        userChallenge.setChallenge(challenge);

        return userChallenge;
    }

}
