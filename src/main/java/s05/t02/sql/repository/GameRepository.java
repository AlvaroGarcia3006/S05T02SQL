package s05.t02.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s05.t02.sql.model.Game;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {
    void deleteByPlayerId(int playerId);
    List<Game> findByPlayerId(int playerId);

}
