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

import matej.tejkogames.api.repositories.YambChallengeRepository;
import matej.tejkogames.interfaces.services.YambChallengeService;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.models.yamb.YambChallenge;

@Service
public class YambChallengeServiceImpl implements YambChallengeService {

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
    public List<YambChallenge> getAllByIdIn(Set<UUID> idSet) {
        return yambChallengeRepository.findAllById(idSet);
    }

    @Override
    public YambChallenge create(YambChallengeRequest requestBody) {
        YambChallenge yambChallenge = new YambChallenge();

        yambChallenge.updateByRequest(requestBody);

        return yambChallengeRepository.save(yambChallenge);
    }

    @Override
    public YambChallenge updateById(UUID id, YambChallengeRequest requestBody) {
        YambChallenge yambChallenge = getById(id);

        yambChallenge.updateByRequest(requestBody);

        return yambChallengeRepository.save(yambChallenge);
    }

    @Override
    public List<YambChallenge> updateAll(Map<UUID, YambChallengeRequest> idRequestMap) {
        List<YambChallenge> yambChallengeList = getAllByIdIn(idRequestMap.keySet());

        for (YambChallenge yambChallenge : yambChallengeList) {
            yambChallenge.updateByRequest(idRequestMap.get(yambChallenge.getId()));
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
    public void deleteAllById(Set<UUID> idSet) {
        yambChallengeRepository.deleteAllById(idSet);
    }

    @Override
    public boolean hasPermission(UUID id, String username) {
        for (User user : getById(id).getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
