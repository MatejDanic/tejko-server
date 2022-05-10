package matej.tejko.interfaces.api.services;

import java.util.UUID;

import matej.tejko.models.general.Challenge;
import matej.tejko.models.general.payload.requests.ChallengeRequest;

public interface ChallengeServiceInterface extends ServiceInterface<UUID, Challenge, ChallengeRequest> {

}
