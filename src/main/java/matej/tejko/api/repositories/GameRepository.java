package matej.tejko.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejko.models.general.Game;

public interface GameRepository extends JpaRepository<Game, UUID> {

}
