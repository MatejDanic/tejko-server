package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.interfaces.api.ObjectServiceInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;

public interface PreferenceServiceInterface extends ObjectServiceInterface<UUID, Preference, PreferenceRequest, PreferenceResponse> {
        
    public PreferenceResponse getByUserId(UUID userId);

}
