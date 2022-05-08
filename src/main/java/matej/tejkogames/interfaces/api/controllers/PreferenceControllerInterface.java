package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

public interface PreferenceControllerInterface extends ControllerInterface<Preference, UUID, PreferenceRequest> {

}
