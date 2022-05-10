package matej.tejko.interfaces.api.services;

import java.util.UUID;

import matej.tejko.models.general.payload.requests.YambRequest;
import matej.tejko.models.yamb.Yamb;

public interface YambServiceInterface extends ServiceInterface<UUID, Yamb, YambRequest> {

}
