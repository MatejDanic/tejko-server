package matej.tejkogames.interfaces.api.services;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;

public interface YambChallengeService extends ServiceInterface<YambChallenge, UUID, YambChallengeRequest> {

}
