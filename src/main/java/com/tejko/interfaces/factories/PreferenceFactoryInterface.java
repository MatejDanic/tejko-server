package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;

public interface PreferenceFactoryInterface extends DatabaseEntityFactory<Preference, PreferenceRequest> {

}
