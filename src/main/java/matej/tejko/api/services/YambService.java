package matej.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejko.api.repositories.UserRepository;
import matej.tejko.api.repositories.YambRepository;
import matej.tejko.constants.TejkoConstants;
import matej.tejko.api.repositories.ScoreRepository;
import matej.tejko.api.repositories.GameRepository;
import matej.tejko.exceptions.IllegalActionException;
import matej.tejko.factories.GameFactory;
import matej.tejko.factories.ScoreFactory;
import matej.tejko.interfaces.api.services.YambServiceInterface;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.payload.requests.ScoreRequest;
import matej.tejko.models.general.payload.requests.YambRequest;
import matej.tejko.models.yamb.Box;
import matej.tejko.models.yamb.Dice;
import matej.tejko.models.yamb.Yamb;
import matej.tejko.models.yamb.enums.BoxType;
import matej.tejko.models.yamb.enums.ColumnType;

@Service
public class YambService implements YambServiceInterface {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    YambRepository yambRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    GameRepository gamesRepository;

    @Autowired
    GameFactory gameFactory;

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
        Yamb yamb = (Yamb) gameFactory.create(objectRequest);
        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> createBulk(List<YambRequest> objectRequestList) {
        List<Yamb> yambList = new ArrayList<>();

        for (YambRequest objectRequest : objectRequestList) {
            yambList.add((Yamb) gameFactory.create(objectRequest));
        }

        return yambRepository.saveAll(yambList);
    }

    @Override
    public Yamb updateById(UUID id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        Yamb yamb = getById(id);

        yamb = applyPatch(yamb, objectPatch);

        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<Yamb> yambList = getBulkById(idObjectPatchMap.keySet());

        for (Yamb yamb : yambList) {
            yamb = applyPatch(yamb, idObjectPatchMap.get(yamb.getId()));
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

    public Set<Dice> rollDiceById(UUID id) throws IllegalActionException {

        Yamb yamb = getById(id);

        if (yamb.getRollCount() == 0) {
            yamb.unfreezeAllDice();
        } else if (yamb.getRollCount() == 3) {
            throw new IllegalActionException("Roll limit has been reached!");
        } else if (yamb.getRollCount() == 1 && yamb.getAnnouncement() == null
                && yamb.getForm().isAnnouncementRequired()) {
            throw new IllegalActionException("Announcement is required!");
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

    public BoxType announceById(UUID id, BoxType announcement) throws IllegalActionException {

        Yamb yamb = getById(id);

        if (yamb.getAnnouncement() != null) {
            throw new IllegalActionException("Announcement is already declared!");
        } else if (yamb.getRollCount() != 1) {
            throw new IllegalActionException("Announcement is available only after first roll!");
        }

        yamb.setAnnouncement(announcement);
        yambRepository.save(yamb);

        return yamb.getAnnouncement();
    }

    public Yamb fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException {

        Yamb yamb = getById(id);

        Box selectedBox = yamb.getForm().getColumnByType(columnType).getBoxByType(boxType);

        if (selectedBox.isFilled()) {
            throw new IllegalActionException("Box is already filled!");
        } else if (!selectedBox.isAvailable()) {
            throw new IllegalActionException("Box is currently unavailable!");
        } else if (yamb.getRollCount() == 0) {
            throw new IllegalActionException("First roll is mandatory!");
        } else if (yamb.getAnnouncement() != null && yamb.getAnnouncement() != selectedBox.getType()) {
            throw new IllegalActionException("Announcement is different from selected box!");
        } else if (yamb.getForm().getColumnByType(columnType).isLocked()) {
            throw new IllegalActionException("Column is currently locked");
        }

        yamb.fill(columnType, boxType);

        if (yamb.getForm().getAvailableBoxes() == 0) {
            Score score = scoreFactory.create(new ScoreRequest(yamb.getUser().getId(), TejkoConstants.APP_YAMB_ID,
                    yamb.getForm().getTotalSum()));
            scoreRepository.save(score);
        }

        yambRepository.save(yamb);

        return yamb;
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return getById(objectId).getUser().getId().equals(userId);
    }

    @Override
    public Yamb applyPatch(Yamb object, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, Yamb.class);
    }

}
