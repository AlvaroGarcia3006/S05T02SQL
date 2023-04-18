package s05.t02.sql.service;
import s05.t02.sql.model.dto.GameDTO;

import java.util.List;

public interface GameService {

    GameDTO createGame(int playerId);
    void deleteGamesByPlayerId(int playerId);
    List<GameDTO> findGamesByPlayerId(int playerId);
    int rollDice();
}
