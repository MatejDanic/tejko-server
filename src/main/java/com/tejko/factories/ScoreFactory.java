package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.ScoreFactoryInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.general.payload.requests.YambScoreRequest;
import com.tejko.models.yamb.YambScore;

@Component
public class ScoreFactory implements ScoreFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Score getObject(ScoreRequest scoreRequest) {

        if (YambRequest.class.isInstance(scoreRequest)) {
            return getYambScoreObject((YambScoreRequest) scoreRequest);
        }

        return null;
    }

    private YambScore getYambScoreObject(YambScoreRequest yambScoreRequest) {
        User user = userRepository.findById(yambScoreRequest.getUserId()).get();
        
        return YambScore.create(user, yambScoreRequest.getValue());
    }

}
