package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;

public interface PreferenceControllerInterface extends ControllerInterface<UUID, Preference, PreferenceRequest> {

}
