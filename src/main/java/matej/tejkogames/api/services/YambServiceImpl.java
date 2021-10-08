package matej.tejkogames.api.services;

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
import matej.tejkogames.api.repositories.TejkoGameRepository;
import matej.tejkogames.exceptions.IllegalMoveException;
import matej.tejkogames.factories.ScoreFactory;
import matej.tejkogames.factories.YambFactory;
import matej.tejkogames.interfaces.services.YambService;
import matej.tejkogames.utils.YambUtil;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.yamb.Box;
import matej.tejkogames.models.yamb.BoxType;
import matej.tejkogames.models.yamb.Column;
import matej.tejkogames.models.yamb.ColumnType;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.Yamb;
import matej.tejkogames.models.yamb.YambForm;

@Service
public class YambServiceImpl implements YambService {

    @Autowired
    YambRepository yambRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    TejkoGameRepository tejkoGamesRepository;

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
    public List<Yamb> getAllByIdIn(Set<UUID> idSet) {
        return yambRepository.findAllById(idSet);
    }

    @Override
    public Yamb create(YambRequest requestBody) {
        Yamb yamb = yambFactory.createYamb(requestBody.getUser(), requestBody.getType(),
                requestBody.getNumberOfColumns(), requestBody.getNumberOfDice(), requestBody.getChallenge());
        return yambRepository.save(yamb);
    }

    @Override
    public Yamb updateById(UUID id, YambRequest requestBody) {
        Yamb yamb = getById(id);

        yamb.updateByRequest(requestBody);

        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> updateAll(Map<UUID, YambRequest> idRequestMap) {
        List<Yamb> yambList = getAllByIdIn(idRequestMap.keySet());

        for (Yamb yamb : yambList) {
            yamb.updateByRequest(idRequestMap.get(yamb.getId()));
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
    public void deleteAllById(Set<UUID> idSet) {
        yambRepository.deleteAllById(idSet);
    }

    public Set<Dice> rollDiceById(UUID id) throws IllegalMoveException {
        Yamb yamb = getById(id);
        Set<Dice> diceSet = yamb.getDiceSet();
        YambForm form = yamb.getForm();
        if (yamb.getRollCount() == 0) {
            for (Dice dice : diceSet) {
                dice.setHeld(false);
            }
        } else if (yamb.getRollCount() == 3) {
            throw new IllegalMoveException("Roll limit reached!");
        } else if (yamb.getRollCount() == 1 && yamb.getAnnouncement() == null && form.isAnnouncementRequired()) {
            throw new IllegalMoveException("Announcement is required!");
        }
        if (yamb.getRollCount() < 3)
            yamb.setRollCount(yamb.getRollCount() + 1);

        for (Dice dice : diceSet) {
            if (!dice.isHeld()) {
                dice.roll();
            }
        }
        yamb.setDiceSet(diceSet);
        yambRepository.save(yamb);

        return diceSet;
    }

    public Set<Dice> holdDiceById(UUID id, int order) {
        Yamb yamb = getById(id);
        Set<Dice> diceSet = yamb.getDiceSet();
        for (Dice dice : diceSet) {
            if (dice.getOrder() == order) {
                dice.setHeld(!dice.isHeld());
                break;
            }
        }
        yamb.setDiceSet(diceSet);
        yambRepository.save(yamb);

        return diceSet;
    }

    public BoxType announceById(UUID id, BoxType announcement) throws IllegalMoveException {

        Yamb yamb = getById(id);

        if (yamb.getAnnouncement() != null) {
            throw new IllegalMoveException("Announcement already declared!");
        } else if (yamb.getRollCount() != 1) {
            throw new IllegalMoveException("Announcement is available only after first roll!");
        }

        yamb.setAnnouncement(announcement);
        yambRepository.save(yamb);

        return yamb.getAnnouncement();
    }

    public Yamb fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalMoveException {

        Yamb yamb = getById(id);
        YambForm form = yamb.getForm();
        Set<Dice> diceSet = yamb.getDiceSet();

        Box selectedBox = form.getColumnByType(columnType).getBoxByType(boxType);

        if (selectedBox.isFilled()) {
            throw new IllegalMoveException("Box is already filled!");
        } else if (!selectedBox.isAvailable()) {
            throw new IllegalMoveException("Box is not available!");
        } else if (yamb.getRollCount() == 0) {
            throw new IllegalMoveException("Cannot fill box without rolling dice first!");
        } else if (yamb.getAnnouncement() != null && yamb.getAnnouncement() != selectedBox.getType()) {
            throw new IllegalMoveException("Another box is announced");
        }

        for (Column column : form.getColumns()) {
            if (column.getType() == columnType) {
                for (Box box : column.getBoxes()) {
                    if (box.getType() == boxType) {
                        box.fill(YambUtil.calculateScore(diceSet, box.getType()));
                    } else if (column.getType() == ColumnType.DOWNWARDS && boxType != BoxType.YAMB
                            && BoxType.valueOf(boxType.name()).ordinal() + 1 == box.getType().ordinal()) {
                        box.setAvailable(true);
                    } else if (column.getType() == ColumnType.UPWARDS && boxType != BoxType.ONES
                            && BoxType.valueOf(boxType.name()).ordinal() - 1 == box.getType().ordinal()) {
                        box.setAvailable(true);
                    }
                }
                break;
            }
        }

        form.updateSums(columnType);
        form.setAvailableBoxes(form.getAvailableBoxes() - 1);

        if (form.getAvailableBoxes() == 0) {
            Score score = scoreFactory.createScore(yamb.getUser(), form.getTotalSum());
            scoreRepository.save(score);
        }

        for (Dice dice : diceSet) {
            dice.setHeld(false);
        }

        yamb.setDiceSet(diceSet);
        yamb.setForm(form);
        yamb.setRollCount(0);
        yamb.setAnnouncement(null);

        yambRepository.save(yamb);

        return yamb;
    }

    public boolean hasPermission(UUID id, String username) {
        return getById(id).getUser().getUsername().equals(username);
    }

}
