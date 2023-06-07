package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tejko.interfaces.mappers.UserChallengeMapperInterface;
import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

public class UserChallengeMapper implements UserChallengeMapperInterface {

    @Override
    public UserChallengeResponse toApiResponse(UserChallenge userChallenge) {
        return new UserChallengeResponse(
            userChallenge.getCreatedDate(), 
            userChallenge.getLastModifiedDate(), 
            userChallenge.getUser().getId(), 
            userChallenge.getChallenge().getId(), 
            userChallenge.getGame().getId()
        );
    }

    @Override
    public List<UserChallengeResponse> toApiResponseList(List<UserChallenge> userChallengeList) {
        return userChallengeList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
