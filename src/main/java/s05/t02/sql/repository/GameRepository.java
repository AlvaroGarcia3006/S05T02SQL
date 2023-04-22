package s05.t02.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s05.t02.sql.model.Game;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {
    void deleteByPlayerId(Integer playerId);
    List<Game> findByPlayerId(Integer playerId);
    Long countByPlayerId(Integer playerId);
    Long countByPlayerIdAndScore(Integer playerId, Integer score);

}
