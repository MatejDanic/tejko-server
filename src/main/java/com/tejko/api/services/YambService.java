package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.YambRepository;
import com.tejko.api.repositories.GameRepository;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.factories.GameFactory;
import com.tejko.interfaces.api.services.YambServiceInterface;
import com.tejko.mappers.YambMapper;
import com.tejko.models.general.payload.requests.ScoreRequest;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

@Service
public class YambService implements YambServiceInterface {

    @Autowired
    YambRepository yambRepository;

    @Autowired
    GameRepository gamesRepository;
    
    @Resource
    YambMapper yambMapper;

    @Resource
    GameFactory gameFactory;

    @Resource
    ScoreService scoreService;
    
    @Override
    public YambResponse getById(UUID id) {
        return yambMapper.toApiResponse(yambRepository.getById(id));
    }

    @Override
    public List<YambResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return yambMapper.toApiResponseList(yambRepository.findAll(pageable).getContent());
    }

    @Override
    public List<YambResponse> getBulkById(Set<UUID> idSet) {
        return yambMapper.toApiResponseList(yambRepository.findAllById(idSet));
    }

    @Override
    public YambResponse create(YambRequest objectRequest) {
        Yamb yamb = (Yamb) gameFactory.getObject(objectRequest);
        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public List<YambResponse> createBulk(List<YambRequest> objectRequestList) {
        List<Yamb> yambList = new ArrayList<>();

        for (YambRequest objectRequest : objectRequestList) {
            yambList.add((Yamb) gameFactory.getObject(objectRequest));
        }

        return yambMapper.toApiResponseList(yambRepository.saveAll(yambList));
    }

    @Override
    public YambResponse updateById(UUID id, YambRequest yambRequest) {
        Yamb yamb = yambRepository.getById(id);

        yamb = applyPatch(yamb, yambRequest);

        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public List<YambResponse> updateBulkById(Map<UUID, YambRequest> idYambRequestMap) {
        List<Yamb> yambList = yambRepository.findAllById(idYambRequestMap.keySet());

        for (Yamb yamb : yambList) {
            yamb = applyPatch(yamb, idYambRequestMap.get(yamb.getId()));
        }

        return yambMapper.toApiResponseList(yambRepository.saveAll(yambList));
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

    public YambResponse rollDiceById(UUID id, List<Integer> diceToRoll) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.rollDice(diceToRoll);

        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse announceById(UUID id, BoxType boxType) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.announce(boxType);

        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException {
        Yamb yamb = yambRepository.getById(id);

        yamb.fillBox(columnType, boxType);

        if (yamb.getSheet().isCompleted()) {
            scoreService.create(new ScoreRequest(
                yamb.getUser().getId(),
                yamb.getApp().getId(), 
                yamb.getSheet().getTotalSum()
            ));
        }

        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public YambResponse restartById(UUID id) {
        Yamb yamb = yambRepository.getById(id);
        yamb.restart();
        return yambMapper.toApiResponse(yambRepository.save(yamb));
    }

    @Override
    public Yamb applyPatch(Yamb yamb, YambRequest yambRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toApiResponseList'");
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return yambRepository.getById(objectId).getUser().getId().equals(userId);
    }

}
