package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.AppRepository;
import com.tejko.interfaces.factories.ChallengeFactoryInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.App;
import com.tejko.models.general.payload.requests.ChallengeRequest;

@Component
public class ChallengeFactory implements ChallengeFactoryInterface {

    @Autowired
    AppRepository appRepository;

    @Override
    public Challenge getObject(ChallengeRequest objectRequest) {
        Challenge challenge = new Challenge();

        App app = appRepository.findById(objectRequest.getAppId()).get();
        challenge.setApp(app);
        
        return challenge;
    }

}
