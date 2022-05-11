package com.tejko.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.App;

public interface AppRepository extends JpaRepository<App, Integer> {

    App findByName(String name);

}
