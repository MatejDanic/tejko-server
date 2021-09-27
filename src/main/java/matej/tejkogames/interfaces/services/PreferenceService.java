package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

public interface PreferenceService extends ServiceInterface<Preference, UUID, PreferenceRequest> {
    
    public boolean hasPermission(UUID id, String username);

}
