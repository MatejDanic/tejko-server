package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.PreferenceFactoryInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.User;
import com.tejko.models.general.payload.requests.PreferenceRequest;

@Component
public class PreferenceFactory implements PreferenceFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Preference getObject(PreferenceRequest preferenceRequest) {
        User user = userRepository.getById(preferenceRequest.getUserId());

        return Preference.create(
            user, 
            preferenceRequest.getVolumeLevel(), 
            preferenceRequest.getTheme()
        );
    }

}
