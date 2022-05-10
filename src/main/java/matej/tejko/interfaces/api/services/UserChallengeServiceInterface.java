package matej.tejko.interfaces.api.services;

import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;
import matej.tejko.models.general.payload.requests.UserChallengeRequest;

public interface UserChallengeServiceInterface
        extends ServiceInterface<UserChallengeId, UserChallenge, UserChallengeRequest> {

}
