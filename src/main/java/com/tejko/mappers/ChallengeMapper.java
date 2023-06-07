package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.tejko.interfaces.mappers.ChallengeMapperInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.responses.ChallengeResponse;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

public class ChallengeMapper implements ChallengeMapperInterface {

    @Resource
    UserChallengeMapper userChallengeMapper;

    @Override
    public ChallengeResponse toApiResponse(Challenge challenge) {
        List<UserChallengeResponse> userChallenges = userChallengeMapper.toApiResponseList(challenge.getUserChallenges().stream().collect(Collectors.toList()));
        return new ChallengeResponse(
            challenge.getCreatedDate(), 
            challenge.getLastModifiedDate(), 
            challenge.getId(), 
            challenge.getApp().getId(), 
            userChallenges
        );
    }

    @Override
    public List<ChallengeResponse> toApiResponseList(List<Challenge> challengeList) {
        return challengeList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
