package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.AppRepository;
import com.tejko.api.repositories.RoleRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.constants.TejkoConstants;
import com.tejko.interfaces.factories.GameFactoryInterface;
import com.tejko.models.general.App;
import com.tejko.models.general.Game;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.GameRequest;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;

@Component
public class GameFactory implements GameFactoryInterface {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Override
    public Game getObject(GameRequest gameRequest) {

        if (YambRequest.class.isInstance(gameRequest)) {
            return getYambObject((YambRequest) gameRequest);
        }

        return null;
    }

    private Yamb getYambObject(YambRequest yambRequest) {        
        App app = appRepository.findByName(TejkoConstants.APP_YAMB_NAME);
        User user = userRepository.findById(yambRequest.getUserId()).get();
        
        return Yamb.create(app, user);
    }

}
