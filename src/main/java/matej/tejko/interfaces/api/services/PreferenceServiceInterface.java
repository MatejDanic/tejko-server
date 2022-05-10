package matej.tejko.interfaces.api.services;

import java.util.UUID;

import matej.tejko.models.general.Preference;
import matej.tejko.models.general.payload.requests.PreferenceRequest;

public interface PreferenceServiceInterface extends ServiceInterface<UUID, Preference, PreferenceRequest> {

}
