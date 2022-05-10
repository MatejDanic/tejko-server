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

import matej.tejko.api.repositories.UserChallengeRepository;
import matej.tejko.factories.UserChallengeFactory;
import matej.tejko.interfaces.api.services.UserChallengeServiceInterface;
import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;
import matej.tejko.models.general.payload.requests.UserChallengeRequest;

@Service
public class UserChallengeService implements UserChallengeServiceInterface {

    @Autowired
    UserChallengeRepository userChallengeRepository;

    @Autowired
    UserChallengeFactory userChallengeFactory;

    @Override
    public UserChallenge getById(UserChallengeId id) {
        return userChallengeRepository.findById(id).get();
    }

    @Override
    public List<UserChallenge> getBulkById(Set<UserChallengeId> idSet) {
        return userChallengeRepository.findAllById(idSet);
    }

    @Override
    public List<UserChallenge> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return userChallengeRepository.findAll(pageable).getContent();
    }

    @Override
    public UserChallenge create(UserChallengeRequest objectRequest) {
        UserChallenge userChallenge = userChallengeFactory.create(objectRequest);
        return userChallengeRepository.save(userChallenge);
    }

    @Override
    public List<UserChallenge> createBulk(List<UserChallengeRequest> objectRequestList) {
        List<UserChallenge> userChallengeList = new ArrayList<>();

        for (UserChallengeRequest objectRequest : objectRequestList) {
            userChallengeList.add(userChallengeFactory.create(objectRequest));
        }

        return userChallengeRepository.saveAll(userChallengeList);
    }

    @Override
    public UserChallenge updateById(UserChallengeId id, JsonPatch objectPatch)
            throws JsonProcessingException, JsonPatchException {
        UserChallenge challenge = getById(id);

        challenge = applyPatch(challenge, objectPatch);

        return userChallengeRepository.save(challenge);
    }

    @Override
    public List<UserChallenge> updateBulkById(
            Map<UserChallengeId, JsonPatch> idObjectPatchMap) {
        // TODO: implement
        return null;
    }

    @Override
    public void deleteById(UserChallengeId id) {
        userChallengeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userChallengeRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UserChallengeId> idSet) {
        userChallengeRepository.deleteAllById(idSet);
    }

    @Override
    public boolean hasPermission(UUID userId, UserChallengeId objectId) {
        return getById(objectId).getUser().getId().equals(userId);
    }

    @Override
    public UserChallenge applyPatch(UserChallenge object, JsonPatch patch)
            throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, UserChallenge.class);
    }

}
