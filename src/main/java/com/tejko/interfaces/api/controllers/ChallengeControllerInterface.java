package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

public interface ChallengeControllerInterface extends ObjectControllerInterface<UUID, Challenge, ChallengeRequest, ChallengeResponse> {

}
