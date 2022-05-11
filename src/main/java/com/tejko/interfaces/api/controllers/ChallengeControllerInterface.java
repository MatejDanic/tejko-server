package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.models.general.Challenge;
import com.tejko.models.general.payload.requests.ChallengeRequest;

public interface ChallengeControllerInterface extends ControllerInterface<UUID, Challenge, ChallengeRequest> {

}
