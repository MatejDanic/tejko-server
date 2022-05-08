package matej.tejkogames.interfaces.api.services;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.YambRequest;

public interface YambServiceInterface extends ServiceInterface<Yamb, UUID, YambRequest> {

}
