package s05.t02.sql.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s05.t02.sql.model.Game;
import s05.t02.sql.model.Player;
import s05.t02.sql.model.dto.GameDTO;
import s05.t02.sql.repository.GameRepository;
import s05.t02.sql.repository.PlayerRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class GameServiceImpl implements GameService{

    private static final Integer WINNER_SCORE = 7;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public GameDTO createGame(Integer playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Game game = new Game();
        game.setPlayer(player);
        game.setDice1(rollDice());
        game.setDice2(rollDice());
        game.setScore(game.getDice1() + game.getDice2());
        Game savedGame = gameRepository.save(game);
        return convertToDTO(savedGame);
    }

    @Override
    public void deleteGamesByPlayerId(Integer playerId) {
        gameRepository.deleteByPlayerId(playerId);
    }

    @Override
    public List<GameDTO> findGamesByPlayerId(Integer playerId) {
        List<Game> gameList = gameRepository.findByPlayerId(playerId);
        List<GameDTO> gameDTOList = new ArrayList<>();
        gameList.forEach(game -> gameDTOList.add(this.convertToDTO(game)));
        return gameDTOList;
    }

    @Override
    public Long countGamesByPlayerId(Integer playerId) {
        return gameRepository.countByPlayerId(playerId);
    }

    @Override
    public Long countGamesWonByPlayerId(Integer playerId) {
        return gameRepository.countByPlayerIdAndScore(playerId, WINNER_SCORE);
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        BeanUtils.copyProperties(game, gameDTO);
        gameDTO.setPlayerId(game.getPlayer().getId());
        return gameDTO;
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
