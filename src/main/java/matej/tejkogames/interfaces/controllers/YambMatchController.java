package matej.tejkogames.interfaces.controllers;

import java.util.UUID;

import matej.tejkogames.models.general.payload.requests.YambMatchRequest;
import matej.tejkogames.models.yamb.YambMatch;

public interface YambMatchController extends ControllerInterface<YambMatch, UUID, YambMatchRequest> {
    
}
