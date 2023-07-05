package com.tejko.interfaces.api.controllers;

import java.util.UUID;

import com.tejko.interfaces.api.RestController;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;

public interface PreferenceControllerInterface extends RestController<UUID, Preference, PreferenceRequest, PreferenceResponse> {

}
