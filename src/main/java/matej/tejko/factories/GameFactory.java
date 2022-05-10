package matej.tejko.factories;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.AppRepository;
import matej.tejko.api.repositories.RoleRepository;
import matej.tejko.api.repositories.UserRepository;
import matej.tejko.interfaces.factories.GameFactoryInterface;
import matej.tejko.models.general.App;
import matej.tejko.models.general.Game;
import matej.tejko.models.general.User;
import matej.tejko.utils.YambUtil;
import matej.tejko.models.general.payload.requests.GameRequest;
import matej.tejko.models.general.payload.requests.YambRequest;
import matej.tejko.models.yamb.Yamb;

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

        App app = appRepository.getById(objectRequest.getAppId());

        switch (app.getName()) {
            case "Yamb":
                return createYamb((YambRequest) objectRequest);
        }
        return null;
    }

    private Yamb createYamb(YambRequest objectRequest) {

        Yamb yamb = new Yamb();

        User user = userRepository.getById(objectRequest.getUserId());
        yamb.setUser(user);
        yamb.setNumberOfDice(objectRequest.getNumberOfDice());
        yamb.setFormCode(objectRequest.getFormCode());
        yamb.setForm(YambUtil.generateYambForm(objectRequest.getFormCode()));
        yamb.setDiceSet(YambUtil.generateDiceSet(objectRequest.getNumberOfDice()));
        yamb.setStartDate(LocalDateTime.now());

        return yamb;
    }

}
