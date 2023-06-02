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

import com.tejko.api.repositories.PreferenceRepository;
import com.tejko.api.repositories.RoleRepository;
import com.tejko.api.repositories.ScoreRepository;
import com.tejko.api.repositories.UserChallengeRepository;
import com.tejko.api.repositories.UserRepository;
import com.tejko.exceptions.RoleNotFoundException;
import com.tejko.factories.PreferenceFactory;
import com.tejko.factories.UserFactory;
import com.tejko.interfaces.api.services.UserServiceInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.Role;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.Score;

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

    @Resource
    UserFactory userFactory;

    @Resource
    PreferenceFactory preferenceFactory;

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
        User user = userFactory.getObject(objectRequest);
        return userRepository.save(user);
    }

    @Override
    public List<User> createBulk(List<UserRequest> objectRequestList) {
        List<User> userList = new ArrayList<>();

        for (UserRequest objectRequest : objectRequestList) {
            userList.add(userFactory.getObject(objectRequest));
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
        return getById(id).getPreference();
    }

    @Override
    public void deletePreferenceByUserId(UUID id) {
        preferenceRepository.deleteById(getById(id).getPreference().getId());
    }

    @Override
    public Preference savePreferenceByUserId(UUID id, PreferenceRequest preferenceRequest) {
        return preferenceRepository.save(preferenceFactory.getObject(preferenceRequest));
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