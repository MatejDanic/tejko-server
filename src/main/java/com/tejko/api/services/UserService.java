package com.tejko.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejko.api.repositories.UserRepository;
import com.tejko.exceptions.RoleNotFoundException;
import com.tejko.factories.PreferenceFactory;
import com.tejko.factories.UserFactory;
import com.tejko.interfaces.api.services.UserServiceInterface;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.requests.UserRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.PreferenceResponse;
import com.tejko.models.general.payload.responses.ScoreResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PreferenceService preferenceService;

    @Autowired
    UserChallengeService userChallengeService;

    @Autowired
    ScoreService scoreService;

    @Resource
    UserFactory userFactory;

    @Resource
    PreferenceFactory preferenceFactory;

    @Override
    public UserResponse getById(UUID id) {
        return toApiResponse(userRepository.getById(id));
    }

    @Override
    public List<UserResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(userRepository.findAll(pageable).getContent());
    }

    @Override
    public List<UserResponse> getBulkById(Set<UUID> idSet) {
        return toApiResponseList(userRepository.findAllById(idSet));
    }

    @Override
    public UserResponse create(UserRequest objectRequest) {
        User user = userFactory.getObject(objectRequest);
        return toApiResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> createBulk(List<UserRequest> objectRequestList) {
        List<User> userList = new ArrayList<>();

        for (UserRequest objectRequest : objectRequestList) {
            userList.add(userFactory.getObject(objectRequest));
        }

        return toApiResponseList(userRepository.saveAll(userList));
    }

    @Override
    public UserResponse updateById(UUID id, UserRequest userRequest) {
        User user = userRepository.findById(id).get();

        user = applyPatch(user, userRequest);

        return toApiResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> updateBulkById(Map<UUID, UserRequest> idUserRequestMap) {
        List<User> userList = userRepository.findAllById(idUserRequestMap.keySet());

        for (User user : userList) {
            user = applyPatch(user, idUserRequestMap.get(user.getId()));
        }

        return toApiResponseList(userRepository.saveAll(userList));
    }

    @Override
    public ApiResponse<?> deleteById(UUID id) {
        userRepository.deleteById(id);
        return new ApiResponse<>("User has been deleted successfully.");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        userRepository.deleteAll();
        return new ApiResponse<>("Users have been deleted successfully.");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<UUID> idSet) {
        userRepository.deleteAllById(idSet);
        return new ApiResponse<>("All users have been deleted successfully.");
    }

    @Override
    public PreferenceResponse getPreferenceByUserId(UUID id) {
        return preferenceService.getByUserId(id);
    }

    @Override
    public ApiResponse<?> deletePreferenceByUserId(UUID id) {
        return preferenceService.deleteByUserId(id);
    }

    @Override
    public PreferenceResponse updatePreferenceByUserId(UUID id, PreferenceRequest preferenceRequest) {
        return preferenceService.updateByUserId(id, preferenceRequest);
    }

    @Override
    public UserResponse assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException {
        User user = userRepository.getById(id);
        //user.assignRole(roleService.getById(roleId)).orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " not found."));
        return toApiResponse(userRepository.save(user));
    }

    @Override
    public List<ScoreResponse> getScoresByUserId(UUID id) {
        return scoreService.getScoresByUserId(id);
    }
    
    @Override
    public List<UserResponse> findAllByRolesId(Integer id) {
        return toApiResponseList(userRepository.findAllByRolesId(id));
    }

    @Override
    public UserResponse getByUsername(String username) {
        return toApiResponse(userRepository.findByUsername(username).get());
    }

    @Override
    public boolean hasPermission(UUID userId, UUID objectId) {
        return false;
    }

    @Override
    public User applyPatch(User user, UserRequest userRequest) {
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        } 
        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        return user;
    }

    @Override
    public UserResponse toApiResponse(User object) {
        return new UserResponse(object);
    }

    @Override
    public List<UserResponse> toApiResponseList(List<User> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}