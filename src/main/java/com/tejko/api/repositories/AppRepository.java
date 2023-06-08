package com.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.App;

public interface AppRepository extends JpaRepository<App, UUID> {

    App findByName(String name);

}
