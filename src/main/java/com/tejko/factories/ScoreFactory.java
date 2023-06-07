package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.AppRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.ScoreFactoryInterface;
import com.tejko.models.general.App;
import com.tejko.models.general.Score;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.ScoreRequest;

@Component
public class ScoreFactory implements ScoreFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Override
    public Score getObject(ScoreRequest objectRequest) {
        User user = userRepository.findById(objectRequest.getUserId()).get();
        App app = appRepository.findById(objectRequest.getAppId()).get();
        
        return Score.create(
            app, 
            user, 
            objectRequest.getValue()
        );
    }

}
