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

import com.tejko.api.repositories.UserChallengeRepository;
import com.tejko.factories.UserChallengeFactory;
import com.tejko.interfaces.api.services.UserChallengeServiceInterface;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

@Service
public class UserChallengeService implements UserChallengeServiceInterface {

    @Autowired
    UserChallengeRepository userChallengeRepository;

    @Resource
    UserChallengeFactory userChallengeFactory;

    @Override
    public UserChallengeResponse getById(UserChallengeId id) {
        return toApiResponse(userChallengeRepository.getById(id));
    }

    @Override
    public List<UserChallengeResponse> getBulkById(Set<UserChallengeId> idSet) {
        return toApiResponseList(userChallengeRepository.findAllById(idSet));
    }

    @Override
    public List<UserChallengeResponse> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return toApiResponseList(userChallengeRepository.findAll(pageable).getContent());
    }

    @Override
    public UserChallengeResponse create(UserChallengeRequest objectRequest) {
        UserChallenge userChallenge = userChallengeFactory.getObject(objectRequest);
        return toApiResponse(userChallengeRepository.save(userChallenge));
    }

    @Override
    public List<UserChallengeResponse> createBulk(List<UserChallengeRequest> objectRequestList) {
        List<UserChallenge> userChallengeList = new ArrayList<>();

        for (UserChallengeRequest objectRequest : objectRequestList) {
            userChallengeList.add(userChallengeFactory.getObject(objectRequest));
        }

        return toApiResponseList(userChallengeRepository.saveAll(userChallengeList));
    }

    @Override
    public UserChallengeResponse updateById(UserChallengeId id, UserChallengeRequest userChallengeRequest) {
        UserChallenge userChallenge = userChallengeRepository.getById(id);

        userChallenge = applyPatch(userChallenge, userChallengeRequest);

        return toApiResponse(userChallengeRepository.save(userChallenge));
    }

    @Override
    public List<UserChallengeResponse> updateBulkById(Map<UserChallengeId, UserChallengeRequest> idUserChallengeRequestMap) {
        List<UserChallenge> userChallengeList = userChallengeRepository.findAllById(idUserChallengeRequestMap.keySet());

        for (UserChallenge userChallenge : userChallengeList) {
            userChallenge = applyPatch(userChallenge, idUserChallengeRequestMap.get(userChallenge.getId()));
        }
        return toApiResponseList(userChallengeRepository.saveAll(userChallengeList));
    }

    @Override
    public ApiResponse<?> deleteById(UserChallengeId id) {
        userChallengeRepository.deleteById(id);
        return new ApiResponse<>("User Challenge has been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteAll() {
        userChallengeRepository.deleteAll();
        return new ApiResponse<>("User Challenges have been deleted successfully");
    }

    @Override
    public ApiResponse<?> deleteBulkById(Set<UserChallengeId> idSet) {
        userChallengeRepository.deleteAllById(idSet);
        return new ApiResponse<>("All User Challenges have been deleted successfully");
    }

    @Override
    public boolean hasPermission(UUID userId, UserChallengeId objectId) {
        return userChallengeRepository.getById(objectId).getUser().getId().equals(userId);
    }

    @Override
    public UserChallenge applyPatch(UserChallenge userChallenge, UserChallengeRequest userChallengeRequest) {
        return userChallenge;
    }

    @Override
    public UserChallengeResponse toApiResponse(UserChallenge object) {
        return new UserChallengeResponse(object);
    }

    @Override
    public List<UserChallengeResponse> toApiResponseList(List<UserChallenge> objectList) {
        return objectList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
