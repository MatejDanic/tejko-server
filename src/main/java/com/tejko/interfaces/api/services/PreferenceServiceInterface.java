package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.interfaces.api.RestService;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.PreferenceResponse;

public interface PreferenceServiceInterface extends RestService<UUID, Preference, PreferenceRequest, PreferenceResponse> {
        
    public PreferenceResponse getByUserId(UUID userId);

}
