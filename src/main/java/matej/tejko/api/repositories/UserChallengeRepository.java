package matej.tejko.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, UserChallengeId> {

}
