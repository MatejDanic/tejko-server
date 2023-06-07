package com.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.ResponseWrapper;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {

    Preference getByUserId(UUID userId);

    ResponseWrapper<?> deleteByUserId(UUID id);

}
