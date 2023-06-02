package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

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

import com.tejko.api.repositories.UserRepository;
import com.tejko.api.repositories.YambRepository;
import com.tejko.constants.TejkoConstants;
import com.tejko.api.repositories.ScoreRepository;
import com.tejko.api.repositories.GameRepository;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.factories.GameFactory;
import com.tejko.factories.ScoreFactory;
import com.tejko.interfaces.api.services.YambServiceInterface;
import com.tejko.models.general.Score;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

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

    @Resource
    GameFactory gameFactory;

    @Resource
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
        Yamb yamb = (Yamb) gameFactory.getObject(objectRequest);
        return yambRepository.save(yamb);
    }

    @Override
    public List<Yamb> createBulk(List<YambRequest> objectRequestList) {
        List<Yamb> yambList = new ArrayList<>();

        for (YambRequest objectRequest : objectRequestList) {
            yambList.add((Yamb) gameFactory.getObject(objectRequest));
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

    public Yamb rollDiceById(UUID id, List<Integer> diceToRoll) throws IllegalActionException {
        Yamb yamb = getById(id);

        yamb.rollDice(diceToRoll);

        return yambRepository.save(yamb);
    }

    @Override
    public Yamb announceById(UUID id, BoxType boxType) throws IllegalActionException {
        Yamb yamb = getById(id);

        yamb.announce(boxType);

        return yambRepository.save(yamb);
    }

    @Override
    public Yamb fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException {

        Yamb yamb = getById(id);

        yamb.fillBox(columnType, boxType);

        if (yamb.getSheet().isCompleted()) {
            Score score = scoreFactory.getObject(new ScoreRequest(yamb.getUser().getId(), TejkoConstants.APP_YAMB_ID, yamb.getSheet().getTotalSum()));
            scoreRepository.save(score);
        }

        return yambRepository.save(yamb);
    }

    @Override
    public Yamb restartById(UUID id) {
        Yamb yamb = getById(id);
        yamb.restart();
        return yambRepository.save(yamb);
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
