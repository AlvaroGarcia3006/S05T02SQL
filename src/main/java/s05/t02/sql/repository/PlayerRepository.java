package s05.t02.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s05.t02.sql.model.Player;

public interface PlayerRepository extends JpaRepository <Player, Long>{
    boolean existsByName(String name);
}
