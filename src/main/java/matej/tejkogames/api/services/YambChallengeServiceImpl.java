package matej.tejkogames.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<YambChallenge> getAll() {
        return yambChallengeRepository.findAll();
    }

    @Override
    public YambChallenge updateById(UUID id, YambChallengeRequest requestBody) {
        YambChallenge yambChallenge = getById(id);
        if (requestBody.getUsers() != null) {
            yambChallenge.setUsers(requestBody.getUsers());
        }
        if (requestBody.getYambs() != null) {
            yambChallenge.setYambs(requestBody.getYambs());
        }
        if (requestBody.getScores() != null) {
            yambChallenge.setScores(requestBody.getScores());
        }
        return yambChallengeRepository.save(yambChallenge);
    }

    @Override
    public YambChallenge create(YambChallengeRequest requestBody) {
        YambChallenge yambChallenge = new YambChallenge();
        if (requestBody.getUsers() != null) {
            yambChallenge.setUsers(requestBody.getUsers());
        }
        if (requestBody.getYambs() != null) {
            yambChallenge.setYambs(requestBody.getYambs());
        }
        if (requestBody.getScores() != null) {
            yambChallenge.setScores(requestBody.getScores());
        }
        return yambChallengeRepository.save(yambChallenge);
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
    public boolean hasPermission(UUID id, String username) {
        for (User user : getById(id).getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
