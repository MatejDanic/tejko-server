package matej.tejkogames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejkogames.models.general.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    Game findByName(String name);

}
