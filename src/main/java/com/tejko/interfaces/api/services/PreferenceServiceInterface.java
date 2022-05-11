package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;

public interface PreferenceServiceInterface extends ServiceInterface<UUID, Preference, PreferenceRequest> {

}
