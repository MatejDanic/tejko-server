package com.tejko.api.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tejko.models.general.Score;

public interface ScoreRepository extends JpaRepository<Score, UUID> {

    List<Score> findAllByUserId(UUID userId);

    @Query("FROM YambScore")
    List<Score> findAllYambScores();

    List<Score> findAllByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
