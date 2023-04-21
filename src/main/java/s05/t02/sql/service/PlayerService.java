package s05.t02.sql.service;

import s05.t02.sql.model.Player;
import s05.t02.sql.model.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO updatePlayerName(Integer id, String name);
    List<PlayerDTO> findAllPlayersWithWinRate();
    Double getAverageWinRate();
    PlayerDTO getLoserPlayer();
    PlayerDTO getWinnerPlayer();
}
