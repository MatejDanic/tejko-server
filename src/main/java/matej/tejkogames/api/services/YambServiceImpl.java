package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.UserRepository;
import matej.tejkogames.api.repositories.YambRepository;
import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.api.repositories.GameRepository;
import matej.tejkogames.exceptions.IllegalMoveException;
import matej.tejkogames.factories.ScoreFactory;
import matej.tejkogames.factories.YambFactory;
import matej.tejkogames.interfaces.api.services.YambService;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.yamb.Box;
import matej.tejkogames.models.yamb.BoxType;
import matej.tejkogames.models.yamb.ColumnType;
import matej.tejkogames.models.yamb.Dice;

@Service
public class YambServiceImpl implements YambService {

    @Autowired
    YambRepository yambRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    GameRepository gamesRepository;

    @Autowired
    YambFactory yambFactory;

    @Autowired
    ScoreFactory scoreFactory;

    @Override
    public Yamb getById(UUID id) {
        return yambRepository.findById(id).get();
    }

    @Override
    public List<Yamb> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return yambRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Yamb> getBulkById(Set<UUID> idSet) {
        return yambRepository.findAllById(idSet);
    }

    @Override
    public Yamb create(YambRequest objectRequest) {
        Yamb yamb = yambFactory.createYamb(objectRequest.getUser(), objectRequest.getType(),
                objectRequest.getFormCode(), objectRequest.getNumberOfDice(), objectRequest.getChallenge());
        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> createBulk(List<YambRequest> objectRequestList) {
        List<Yamb> yambList = new ArrayList<>();

        for (YambRequest yambRequest : objectRequestList) {
            Yamb yamb = new Yamb();
            yamb.updateByRequest(yambRequest);
        }

        return yambRepository.saveAll(yambList);
    }

    @Override
    public Yamb updateById(UUID id, YambRequest objectRequest) {
        Yamb yamb = getById(id);

        yamb.updateByRequest(objectRequest);

        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> updateBulkById(Map<UUID, YambRequest> idObjectRequestMap) {
        List<Yamb> yambList = getBulkById(idObjectRequestMap.keySet());

        for (Yamb yamb : yambList) {
            yamb.updateByRequest(idObjectRequestMap.get(yamb.getId()));
        }

        return yambRepository.saveAll(yambList);
    }

    @Override
    public void deleteById(UUID id) {
        yambRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        yambRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        yambRepository.deleteAllById(idSet);
    }

    public Set<Dice> rollDiceById(UUID id) throws IllegalMoveException {

        Yamb yamb = getById(id);

        if (yamb.getRollCount() == 0) {
            yamb.unfreezeAllDice();
        } else if (yamb.getRollCount() == 3) {
            throw new IllegalMoveException("Roll limit has been reached!");
        } else if (yamb.getRollCount() == 1 && yamb.getAnnouncement() == null
                && yamb.getForm().isAnnouncementRequired()) {
            throw new IllegalMoveException("Announcement is required!");
        }

        yamb.rollDice();
        yambRepository.save(yamb);

        return yamb.getDiceSet();
    }

    public Set<Dice> freezeDiceById(UUID id, int order) {
        Yamb yamb = getById(id);

        for (Dice dice : yamb.getDiceSet()) {
            if (dice.getOrder() == order) {
                dice.setFrozen(!dice.isFrozen());
                break;
            }
        }
        yambRepository.save(yamb);

        return yamb.getDiceSet();
    }

    public BoxType announceById(UUID id, BoxType announcement) throws IllegalMoveException {

        Yamb yamb = getById(id);

        if (yamb.getAnnouncement() != null) {
            throw new IllegalMoveException("Announcement is already declared!");
        } else if (yamb.getRollCount() != 1) {
            throw new IllegalMoveException("Announcement is available only after first roll!");
        }

        yamb.setAnnouncement(announcement);
        yambRepository.save(yamb);

        return yamb.getAnnouncement();
    }

    public Yamb fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalMoveException {

        Yamb yamb = getById(id);

        Box selectedBox = yamb.getForm().getColumnByType(columnType).getBoxByType(boxType);

        if (selectedBox.isFilled()) {
            throw new IllegalMoveException("Box is already filled!");
        } else if (!selectedBox.isAvailable()) {
            throw new IllegalMoveException("Box is currently unavailable!");
        } else if (yamb.getRollCount() == 0) {
            throw new IllegalMoveException("First roll is mandatory!");
        } else if (yamb.getAnnouncement() != null && yamb.getAnnouncement() != selectedBox.getType()) {
            throw new IllegalMoveException("Announcement is different from selected box!");
        } else if (yamb.getForm().getColumnByType(columnType).isLocked()) {
            throw new IllegalMoveException("Column is currently locked");
        }

        yamb.fill(columnType, boxType);

        if (yamb.getForm().getAvailableBoxes() == 0) {
            Score score = scoreFactory.createScore(yamb.getUser(), yamb.getForm().getTotalSum());
            scoreRepository.save(score);
        }

        yambRepository.save(yamb);

        return yamb;
    }

}
