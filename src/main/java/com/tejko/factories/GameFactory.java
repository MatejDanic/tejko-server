package com.tejko.factories;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.AppRepository;
import com.tejko.api.repositories.RoleRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.GameFactoryInterface;
import com.tejko.models.general.App;
import com.tejko.models.general.Game;
import com.tejko.models.general.User;
import com.tejko.utils.YambUtil;
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
    public Game create(GameRequest objectRequest) {

        App app = appRepository.findById(objectRequest.getAppId()).get();

        switch (app.getName()) {
            case "Yamb":
                return createYamb((YambRequest) objectRequest);
        }
        return null;
    }

    private Yamb createYamb(YambRequest objectRequest) {

        Yamb yamb = new Yamb();

        User user = userRepository.findById(objectRequest.getUserId()).get();
        yamb.setUser(user);
        yamb.setNumberOfDice(objectRequest.getNumberOfDice());
        yamb.setFormCode(objectRequest.getFormCode());
        yamb.setForm(YambUtil.generateYambForm(objectRequest.getFormCode()));
        yamb.setDiceSet(YambUtil.generateDiceSet(objectRequest.getNumberOfDice()));
        yamb.setStartDate(LocalDateTime.now());

        return yamb;
    }

}
