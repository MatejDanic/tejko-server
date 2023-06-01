package com.tejko.api.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Score;

public interface ScoreRepository extends JpaRepository<Score, UUID> {

    List<Score> findAllByUserId(UUID userId);

    List<Score> findAllByAppId(Integer appId);

    List<Score> findAllByDateBetween(LocalDateTime start, LocalDateTime end);

}
