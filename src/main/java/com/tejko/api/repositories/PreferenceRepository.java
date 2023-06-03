package com.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.responses.ApiResponse;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {

    Preference getByUserId(UUID userId);

    ApiResponse<?> deleteByUserId(UUID id);

}
