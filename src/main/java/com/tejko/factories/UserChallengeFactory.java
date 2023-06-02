package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.ChallengeRepository;
import com.tejko.api.repositories.GameRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.UserChallengeFactoryInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.User;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;

@Component
public class UserChallengeFactory implements UserChallengeFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public UserChallenge getObject(UserChallengeRequest objectRequest) {

        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setId(new UserChallengeId(objectRequest.getUserId(), objectRequest.getChallengeId()));
        
        User user = userRepository.findById(objectRequest.getUserId()).get();
        userChallenge.setUser(user);

        Challenge challenge = challengeRepository.findById(objectRequest.getChallengeId()).get();
        userChallenge.setChallenge(challenge);

        return userChallenge;
    }

}
