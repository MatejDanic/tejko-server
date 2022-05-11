package com.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {

}
