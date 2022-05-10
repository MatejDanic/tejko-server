package matej.tejko.api.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejko.models.general.Score;

public interface ScoreRepository extends JpaRepository<Score, UUID> {

    List<Score> findAllByUserId(UUID id);

    List<Score> findAllByAppId(Integer id);

    List<Score> findAllByDateBetween(LocalDateTime start, LocalDateTime end);

}
