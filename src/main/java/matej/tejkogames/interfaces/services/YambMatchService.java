package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.general.payload.requests.YambMatchRequest;
import matej.tejkogames.models.yamb.YambMatch;

public interface YambMatchService extends ServiceInterface<YambMatch, UUID, YambMatchRequest> {

    public boolean hasPermission(UUID id, String username);

}
