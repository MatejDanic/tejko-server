package matej.tejko.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import matej.tejko.api.repositories.UserRepository;
import matej.tejko.interfaces.factories.PreferenceFactoryInterface;
import matej.tejko.models.general.Preference;
import matej.tejko.models.general.payload.requests.PreferenceRequest;

@Component
public class PreferenceFactory implements PreferenceFactoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Preference create(PreferenceRequest objectRequest) {

        Preference preference = new Preference();

        preference.setUser(userRepository.getById(objectRequest.getUserId()));
        preference.setVolume(objectRequest.getVolume());
        preference.setTheme(objectRequest.getTheme());

        return preference;
    }

}
