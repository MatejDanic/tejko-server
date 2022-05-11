package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;

public interface ChallengeServiceInterface extends ServiceInterface<UUID, Challenge, ChallengeRequest> {

}
