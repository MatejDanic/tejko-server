package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

public interface ChallengeServiceInterface extends ServiceInterface<UUID, Challenge, ChallengeRequest, ChallengeResponse> {

}
