package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;

public interface ChallengeControllerInterface extends ControllerInterface<YambChallenge, UUID, YambChallengeRequest> {

}
