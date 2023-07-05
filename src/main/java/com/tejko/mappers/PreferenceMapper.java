package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tejko.interfaces.mappers.PreferenceMapperInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.responses.PreferenceResponse;

@Component
public class PreferenceMapper implements PreferenceMapperInterface {

    @Override
    public PreferenceResponse toRestResponse(Preference preference) {
        if (preference == null) return null;
        return new PreferenceResponse(
            preference.getId(), 
            preference.getCreatedDate(), 
            preference.getLastModifiedDate(), 
            preference.getVolumeLevel(), 
            preference.getTheme()
        );
    }

    @Override
    public List<PreferenceResponse> toRestResponseList(List<Preference> preferenceList) {
        return preferenceList.stream().map(this::toRestResponse).collect(Collectors.toList());
    }

}
