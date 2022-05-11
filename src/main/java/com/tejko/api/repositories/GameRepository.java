package com.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Game;

public interface GameRepository extends JpaRepository<Game, UUID> {

}
