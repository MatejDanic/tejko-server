package matej.tejkogames.interfaces.controllers;

import java.util.UUID;

import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.models.yamb.YambChallenge;

public interface ChallengeController extends ControllerInterface<YambChallenge, UUID, YambChallengeRequest> {

}
