package matej.tejko.interfaces.api.controllers;

import java.util.UUID;

import matej.tejko.models.general.Preference;
import matej.tejko.models.general.payload.requests.PreferenceRequest;

public interface PreferenceControllerInterface extends ControllerInterface<UUID, Preference, PreferenceRequest> {

}
