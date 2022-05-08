package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.PreferenceRepository;
import matej.tejkogames.interfaces.api.services.PreferenceServiceInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

@Service
public class PreferenceService implements PreferenceServiceInterface {

    @Autowired
    PreferenceRepository preferenceRepository;

    @Override
    public Preference getById(UUID id) {
        return preferenceRepository.getById(id);
    }

    @Override
    public List<Preference> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return preferenceRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Preference> getBulkById(Set<UUID> idSet) {
        return preferenceRepository.findAllById(idSet);
    }

    @Override
    public Preference create(PreferenceRequest objectRequest) {
        Preference preference = new Preference();
        preference.setTheme(objectRequest.getTheme());
        preference.setUser(objectRequest.getUser());
        preference.setVolume(objectRequest.getVolume());
        return preferenceRepository.save(preference);
    }

    @Override
    public List<Preference> createBulk(List<PreferenceRequest> objectRequestList) {
        List<Preference> preferenceList = new ArrayList<>();

        for (PreferenceRequest preferenceRequest : objectRequestList) {
            Preference preference = new Preference();
            preference.updateByRequest(preferenceRequest);
        }

        return preferenceRepository.saveAll(preferenceList);
    }

    @Override
    public Preference updateById(UUID id, PreferenceRequest objectRequest) {
        Preference preference = getById(id);

        preference.updateByRequest(objectRequest);

        return preferenceRepository.save(preference);
    }

    @Override
    public List<Preference> updateBulkById(Map<UUID, PreferenceRequest> idObjectRequestMap) {
        List<Preference> preferenceList = getBulkById(idObjectRequestMap.keySet());

        for (Preference preference : preferenceList) {
            preference.updateByRequest(idObjectRequestMap.get(preference.getId()));
        }

        preferenceRepository.saveAll(preferenceList);
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        preferenceRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        preferenceRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        preferenceRepository.deleteAllById(idSet);
    }

}
