package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.models.yamb.YambChallenge;

public interface YambChallengeService extends ServiceInterface<YambChallenge, UUID, YambChallengeRequest> {
    
    public boolean hasPermission(UUID id, String username);

}
