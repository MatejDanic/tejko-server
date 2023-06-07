package com.tejko.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tejko.interfaces.mappers.PreferenceMapperInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.responses.PreferenceResponse;

public class PreferenceMapper implements PreferenceMapperInterface {

    @Override
    public PreferenceResponse toApiResponse(Preference preference) {
        return new PreferenceResponse(
            preference.getCreatedDate(), 
            preference.getLastModifiedDate(), 
            preference.getId(), 
            preference.getVolumeLevel(), 
            preference.getTheme()
        );
    }

    @Override
    public List<PreferenceResponse> toApiResponseList(List<Preference> preferenceList) {
        return preferenceList.stream().map(this::toApiResponse).collect(Collectors.toList());
    }

}
