package matej.tejkogames.api.services;

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
import matej.tejkogames.interfaces.services.PreferenceService;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;

@Service
public class PreferenceServiceImpl implements PreferenceService {

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
	public List<Preference> getAllByIdIn(Set<UUID> idSet) {
		return preferenceRepository.findAllById(idSet);
    }

    @Override
    public Preference create(PreferenceRequest requestBody) {
        Preference preference = new Preference();
        preference.setTheme(requestBody.getTheme());
        preference.setUser(requestBody.getUser());
        preference.setVolume(requestBody.getVolume());
        return preferenceRepository.save(preference);
    }

    @Override
	public Preference updateById(UUID id, PreferenceRequest requestBody) {
		Preference preference = getById(id);
		
		preference.updateByRequest(requestBody);

		return preferenceRepository.save(preference);
	}
	
	@Override
    public List<Preference> updateAll(Map<UUID, PreferenceRequest> idRequestMap) {
		List<Preference> preferenceList = getAllByIdIn(idRequestMap.keySet());
		
		for (Preference preference : preferenceList) {
			preference.updateByRequest(idRequestMap.get(preference.getId()));
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
    public boolean hasPermission(UUID id, String username) {
        return preferenceRepository.getById(id).getUser().getUsername().equals(username);
    }

}
