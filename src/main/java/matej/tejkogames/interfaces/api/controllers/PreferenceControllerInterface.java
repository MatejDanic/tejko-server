package matej.tejkogames.interfaces.api.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import matej.tejkogames.interfaces.api.ControllerInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

public interface PreferenceControllerInterface extends ControllerInterface<Preference, UUID, PreferenceRequest> {

    public ResponseEntity<Preference> updateById(UUID id, PreferenceRequest preferenceRequest);

}