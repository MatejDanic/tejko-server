package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.ApiResponse;
import com.tejko.models.general.payload.responses.PreferenceResponse;

public interface PreferenceServiceInterface extends ServiceInterface<UUID, Preference, PreferenceRequest, PreferenceResponse> {

    public PreferenceResponse getByUserId(UUID userId);
    
    public ApiResponse<?> deleteByUserId(UUID userId);

    public PreferenceResponse updateByUserId(UUID userId, PreferenceRequest preferenceRequest);

}
