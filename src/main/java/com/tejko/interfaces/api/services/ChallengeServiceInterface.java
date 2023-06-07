package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

public interface ChallengeServiceInterface extends ObjectServiceInterface<UUID, Challenge, ChallengeRequest, ChallengeResponse> {

}
