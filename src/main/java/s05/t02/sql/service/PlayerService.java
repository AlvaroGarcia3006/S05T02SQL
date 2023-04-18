package s05.t02.sql.service;

import s05.t02.sql.model.Player;
import s05.t02.sql.model.dto.PlayerDTO;

public interface PlayerService {
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO updatePlayerName(Integer id, String name);
}
