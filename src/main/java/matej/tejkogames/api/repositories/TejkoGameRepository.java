package matej.tejkogames.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejkogames.models.general.TejkoGame;

public interface TejkoGameRepository extends JpaRepository<TejkoGame, Integer> {

    TejkoGame findByName(String name);

}
