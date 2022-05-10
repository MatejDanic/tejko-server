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

import matej.tejko.api.repositories.PreferenceRepository;
import matej.tejko.api.repositories.RoleRepository;
import matej.tejko.api.repositories.ScoreRepository;
import matej.tejko.api.repositories.UserChallengeRepository;
import matej.tejko.api.repositories.UserRepository;
import matej.tejko.constants.TejkoConstants;
import matej.tejko.exceptions.RoleNotFoundException;
import matej.tejko.factories.UserFactory;
import matej.tejko.interfaces.api.services.UserServiceInterface;
import matej.tejko.models.general.Preference;
import matej.tejko.models.general.Role;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.UserRequest;
import matej.tejko.models.general.Score;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    UserChallengeRepository userChallengeRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserFactory userFactory;

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public List<User> getBulkById(Set<UUID> idSet) {
        return userRepository.findAllById(idSet);
    }

    @Override
    public User create(UserRequest objectRequest) {
        User user = userFactory.create(objectRequest);
        return userRepository.save(user);
    }

    @Override
    public List<User> createBulk(List<UserRequest> objectRequestList) {
        List<User> userList = new ArrayList<>();

        for (UserRequest objectRequest : objectRequestList) {
            userList.add(userFactory.create(objectRequest));
        }

        return userRepository.saveAll(userList);
    }

    @Override
    public User updateById(UUID id, JsonPatch objectPatch) throws JsonProcessingException, JsonPatchException {
        User user = getById(id);

        user = applyPatch(user, objectPatch);

        return userRepository.save(user);
    }

    @Override
    public List<User> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        List<User> userList = getBulkById(idObjectPatchMap.keySet());

        for (User user : userList) {
            user = applyPatch(user, idObjectPatchMap.get(user.getId()));
        }

        return userRepository.saveAll(userList);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        userRepository.deleteAllById(idSet);
    }

    @Override
    public Preference getPreferenceByUserId(UUID id) {
        Preference preference;
        if (getById(id).getPreference() != null) {
            preference = getById(id).getPreference();
        } else {
            preference = savePreferenceByUserId(id);
        }
        return preference;
    }

    @Override
    public void deletePreferenceByUserId(UUID id) {
        preferenceRepository.deleteById(getById(id).getPreference().getId());
    }

    @Override
    public Preference savePreferenceByUserId(UUID id) {
        return preferenceRepository
                .save(new Preference(TejkoConstants.DEFAULT_VOLUME, TejkoConstants.DEFAULT_THEME));
    }

    @Override
    public Set<Role> assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException {

        User user = userRepository.findById(id).get();
        user.assignRole(roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " not found.")));
        userRepository.save(user);

        return user.getRoles();
    }

    @Override
    public List<Score> getScoresByUserId(UUID id) {
        return scoreRepository.findAllByUserId(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return false;
    }

    @Override
    public User applyPatch(User object, JsonPatch patch)
            throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(object, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

}