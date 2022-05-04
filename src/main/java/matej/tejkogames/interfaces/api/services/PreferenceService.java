package matej.tejkogames.interfaces.api.services;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ServiceInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

public interface PreferenceService extends ServiceInterface<Preference, UUID, PreferenceRequest> {

}
