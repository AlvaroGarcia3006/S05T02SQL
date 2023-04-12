package s05.t02.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s05.t02.sql.model.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
