package matej.tejkogames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.general.ids.UserYambChallengeId;

public interface UserYambChallengeRepository extends JpaRepository<UserYambChallenge, UserYambChallengeId> {

}
