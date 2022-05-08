package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.UserYambChallengeRepository;
import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.general.ids.UserYambChallengeId;
import matej.tejkogames.models.general.payload.requests.UserYambChallengeRequest;

@Service
public class UserYambChallengeService {

    @Autowired
    UserYambChallengeRepository userYambChallengeRepository;

    public UserYambChallenge getById(UserYambChallengeId id) {
        return userYambChallengeRepository.findById(id).get();
    }

    public List<UserYambChallenge> getAll() {
        return userYambChallengeRepository.findAll();
    }

    public List<UserYambChallenge> getBulkById(Set<UserYambChallengeId> idSet) {
        return userYambChallengeRepository.findAllById(idSet);
    }

    public UserYambChallenge create(UserYambChallengeRequest objectRequest) {
        UserYambChallenge userYambChallenge = new UserYambChallenge();
        userYambChallenge.setId(new UserYambChallengeId());

        userYambChallenge.updateByRequest(objectRequest);

        return userYambChallengeRepository.save(userYambChallenge);
    }

    public List<UserYambChallenge> createBulk(List<UserYambChallengeRequest> objectRequestList) {
        List<UserYambChallenge> userYambChallengeList = new ArrayList<>();

        for (UserYambChallengeRequest userYambChallengeRequest : objectRequestList) {
            UserYambChallenge userYambChallenge = new UserYambChallenge();
            userYambChallenge.setId(new UserYambChallengeId());
            userYambChallenge.updateByRequest(userYambChallengeRequest);
        }

        return userYambChallengeRepository.saveAll(userYambChallengeList);
    }

    public UserYambChallenge updateById(UserYambChallengeId id, UserYambChallengeRequest objectRequest) {
        UserYambChallenge yambChallenge = getById(id);

        yambChallenge.updateByRequest(objectRequest);

        return userYambChallengeRepository.save(yambChallenge);
    }

    public List<UserYambChallenge> updateBulkById(
            Map<UserYambChallengeId, UserYambChallengeRequest> idObjectRequestMap) {
        // TODO: implement
        return null;
    }

    public void deleteById(UserYambChallengeId id) {
        userYambChallengeRepository.deleteById(id);
    }

    public void deleteAll() {
        userYambChallengeRepository.deleteAll();
    }

    public void deleteBulkById(Set<UserYambChallengeId> idSet) {
        userYambChallengeRepository.deleteAllById(idSet);
    }

}
