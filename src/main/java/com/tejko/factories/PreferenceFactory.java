package com.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tejko.api.repositories.UserRepository;
import com.tejko.interfaces.factories.PreferenceFactoryInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;

@Component
public class PreferenceFactory implements PreferenceFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Preference create(PreferenceRequest objectRequest) {

        Preference preference = new Preference();

        preference.setUser(userRepository.findById(id).get();objectRequest.getUserId()));
        preference.setVolume(objectRequest.getVolume());
        preference.setTheme(objectRequest.getTheme());

        return preference;
    }

}
