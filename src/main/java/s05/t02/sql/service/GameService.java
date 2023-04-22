package s05.t02.sql.service;
import s05.t02.sql.model.dto.GameDTO;

import java.util.List;

public interface GameService {

    GameDTO createGame(Integer playerId);
    void deleteGamesByPlayerId(Integer playerId);
    List<GameDTO> findGamesByPlayerId(Integer playerId);
    Long countGamesByPlayerId(Integer playerId);
    Long countGamesWonByPlayerId(Integer playerId);
}
