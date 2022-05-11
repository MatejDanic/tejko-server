package com.tejko.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.UserChallenge;
import com.tejko.models.general.ids.UserChallengeId;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, UserChallengeId> {

}
