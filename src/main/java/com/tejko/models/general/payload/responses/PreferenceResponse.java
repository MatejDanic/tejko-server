package com.tejko.models.general.payload.responses;

import com.tejko.models.general.Preference;
import com.tejko.models.general.enums.ResponseStatus;

public class PreferenceResponse extends ApiResponse<Preference> {

    public PreferenceResponse(ResponseStatus status, String message, Preference object) {
        super(status, message, object);
    }

    public PreferenceResponse(Preference object) {
        super(object);
    }

}
