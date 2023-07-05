package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.interfaces.api.RestService;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

public interface ChallengeServiceInterface extends RestService<UUID, Challenge, ChallengeRequest, ChallengeResponse> {

}
