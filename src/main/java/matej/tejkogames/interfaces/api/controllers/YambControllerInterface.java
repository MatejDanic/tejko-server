package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.YambRequest;

public interface YambControllerInterface extends ControllerInterface<Yamb, UUID, YambRequest> {

}
