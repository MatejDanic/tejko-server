package matej.tejko.interfaces.api.controllers;

import java.util.UUID;

import matej.tejko.models.general.Challenge;
import matej.tejko.models.general.payload.requests.ChallengeRequest;

public interface ChallengeControllerInterface extends ControllerInterface<UUID, Challenge, ChallengeRequest> {

}
