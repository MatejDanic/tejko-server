package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.LogFactoryInterface;
import com.tejko.models.general.Log;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.LogRequest;

@Component
public class LogFactory implements LogFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Log getObject(LogRequest logRequest) {
        User user = userRepository.getById(logRequest.getUserId());

        return Log.create(
            user, 
            logRequest.getLevel(),
            logRequest.getContent() 
        );
    }

}
