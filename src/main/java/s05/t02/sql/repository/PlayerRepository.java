package s05.t02.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s05.t02.sql.model.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository <Player, Integer>{
    boolean existsByName(String name);
    Optional<Player> findById(Integer id);
}
