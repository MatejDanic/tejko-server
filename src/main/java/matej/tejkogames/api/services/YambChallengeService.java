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

import matej.tejkogames.api.repositories.YambChallengeRepository;
import matej.tejkogames.interfaces.api.services.YambChallengeServiceInterface;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;

@Service
public class YambChallengeService implements YambChallengeServiceInterface {

    @Autowired
    YambChallengeRepository yambChallengeRepository;

    @Override
    public YambChallenge getById(UUID id) {
        return yambChallengeRepository.findById(id).get();
    }

    @Override
    public List<YambChallenge> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return yambChallengeRepository.findAll(pageable).getContent();
    }

    @Override
    public List<YambChallenge> getBulkById(Set<UUID> idSet) {
        return yambChallengeRepository.findAllById(idSet);
    }

    @Override
    public YambChallenge create(YambChallengeRequest objectRequest) {
        YambChallenge yambChallenge = new YambChallenge();

        yambChallenge.updateByRequest(objectRequest);

        return yambChallengeRepository.save(yambChallenge);
    }

    @Override
    public List<YambChallenge> createBulk(List<YambChallengeRequest> objectRequestList) {
        List<YambChallenge> yambChallengeList = new ArrayList<>();

        for (YambChallengeRequest yambChallengeRequest : objectRequestList) {
            YambChallenge yambChallenge = new YambChallenge();
            yambChallenge.updateByRequest(yambChallengeRequest);
        }

        return yambChallengeRepository.saveAll(yambChallengeList);
    }

    @Override
    public YambChallenge updateById(UUID id, YambChallengeRequest objectRequest) {
        YambChallenge yambChallenge = getById(id);

        yambChallenge.updateByRequest(objectRequest);

        return yambChallengeRepository.save(yambChallenge);
    }

    @Override
    public List<YambChallenge> updateBulkById(Map<UUID, YambChallengeRequest> idObjectRequestMap) {
        List<YambChallenge> yambChallengeList = getBulkById(idObjectRequestMap.keySet());

        for (YambChallenge yambChallenge : yambChallengeList) {
            yambChallenge.updateByRequest(idObjectRequestMap.get(yambChallenge.getId()));
        }

        return yambChallengeRepository.saveAll(yambChallengeList);
    }

    @Override
    public void deleteById(UUID id) {
        yambChallengeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        yambChallengeRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        yambChallengeRepository.deleteAllById(idSet);
    }

}
