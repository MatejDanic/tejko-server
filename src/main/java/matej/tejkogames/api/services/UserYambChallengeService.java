package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.UserYambChallengeRepository;
import matej.tejkogames.interfaces.api.services.UserYambChallengeServiceInterface;
import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.general.ids.UserYambChallengeId;
import matej.tejkogames.models.general.payload.requests.UserYambChallengeRequest;

@Service
public class UserYambChallengeService implements UserYambChallengeServiceInterface {

    @Autowired
    UserYambChallengeRepository userYambChallengeRepository;

    @Override
    public UserYambChallenge getById(UserYambChallengeId id) {
        return userYambChallengeRepository.findById(id).get();
    }

    @Override
    public List<UserYambChallenge> getBulkById(Set<UserYambChallengeId> idSet) {
        return userYambChallengeRepository.findAllById(idSet);
    }

    @Override
    public List<UserYambChallenge> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return userYambChallengeRepository.findAll(pageable).getContent();
    }

    @Override
    public UserYambChallenge create(UserYambChallengeRequest objectRequest) {

        UserYambChallenge userYambChallenge = new UserYambChallenge();
        userYambChallenge.setId(new UserYambChallengeId());

        userYambChallenge.updateByRequest(objectRequest);

        return userYambChallengeRepository.save(userYambChallenge);
    }

    @Override
    public List<UserYambChallenge> createBulk(List<UserYambChallengeRequest> objectRequestList) {
        List<UserYambChallenge> userYambChallengeList = new ArrayList<>();

        for (UserYambChallengeRequest userYambChallengeRequest : objectRequestList) {
            UserYambChallenge userYambChallenge = new UserYambChallenge();
            userYambChallenge.setId(new UserYambChallengeId());
            userYambChallenge.updateByRequest(userYambChallengeRequest);
        }

        return userYambChallengeRepository.saveAll(userYambChallengeList);
    }

    @Override
    public UserYambChallenge updateById(UserYambChallengeId id, UserYambChallengeRequest objectRequest) {
        UserYambChallenge yambChallenge = getById(id);

        yambChallenge.updateByRequest(objectRequest);

        return userYambChallengeRepository.save(yambChallenge);
    }

    @Override
    public List<UserYambChallenge> updateBulkById(
            Map<UserYambChallengeId, UserYambChallengeRequest> idObjectRequestMap) {
        // TODO: implement
        return null;
    }

    @Override
    public void deleteById(UserYambChallengeId id) {
        userYambChallengeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userYambChallengeRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UserYambChallengeId> idSet) {
        userYambChallengeRepository.deleteAllById(idSet);
    }

}
