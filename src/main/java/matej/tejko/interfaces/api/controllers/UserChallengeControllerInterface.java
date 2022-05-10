package matej.tejko.interfaces.api.controllers;

import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;
import matej.tejko.models.general.payload.requests.UserChallengeRequest;

public interface UserChallengeControllerInterface
        extends ControllerInterface<UserChallengeId, UserChallenge, UserChallengeRequest> {

}
