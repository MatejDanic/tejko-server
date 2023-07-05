package com.tejko.api.services;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.YambRepository;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.factories.GameFactory;
import com.tejko.interfaces.api.services.YambServiceInterface;
import com.tejko.mappers.GameMapper;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.general.payload.requests.YambScoreRequest;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

@Service
public class YambService implements YambServiceInterface {

    @Autowired
    YambRepository yambRepository;
    
    @Resource
    GameMapper gameMapper;

    @Resource
    GameFactory gameFactory;

    @Autowired
    UserService userService;

    @Autowired
    ScoreService scoreService;

    @Override
    public YambResponse create(Principal principal) {
        User user = userService.getEntityByUsername(principal.getName());
        YambRequest yambRequest = new YambRequest(user.getId());
        Yamb yamb = (Yamb) gameFactory.getObject(yambRequest);
        yamb = yambRepository.save(yamb);
        YambResponse yambResponse = (YambResponse) gameMapper.toRestResponse(yamb);
        return yambResponse;
    }

    @Override
    public YambResponse rollDiceById(UUID id, List<Integer> diceToRoll) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.rollDice(diceToRoll);

        return (YambResponse) gameMapper.toRestResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse announceById(UUID id, BoxType boxType) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.announce(boxType);

        return (YambResponse) gameMapper.toRestResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.fillBox(columnType, boxType);

        if (yamb.getSheet().isCompleted()) {
            scoreService.create(new YambScoreRequest(
                yamb.getUser().getId(),
                yamb.getSheet().getTotalSum()
            ));
        }

        return (YambResponse) gameMapper.toRestResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse restartById(UUID id) {
        Yamb yamb = yambRepository.getById(id);
        yamb.restart();
        return (YambResponse) gameMapper.toRestResponse(yambRepository.save(yamb));
    }

    @Override
    public boolean hasPermission(UUID userId, UUID yambId) {
        return yambRepository.getById(yambId).getUser().getId().equals(userId);
    }

}
