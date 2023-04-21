package s05.t02.sql.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s05.t02.sql.model.Game;
import s05.t02.sql.model.Player;
import s05.t02.sql.model.dto.PlayerDTO;
import s05.t02.sql.repository.GameRepository;
import s05.t02.sql.repository.PlayerRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);

        if (playerRepository.existsByName(player.getName())) {
            throw new IllegalArgumentException("Player name already exists");
        }
        player.setRegistrationDate(LocalDateTime.now());
        Player savedPlayer = playerRepository.save(player);
        return convertToDTO(savedPlayer);
    }

    @Override
    public PlayerDTO updatePlayerName(Integer id, String name) {
        if (playerRepository.existsByName(name)) {
            throw new IllegalArgumentException("Player name already exists");
        }
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        player.setName(name);
        Player updatedPlayer = playerRepository.save(player);
        return convertToDTO(updatedPlayer);
    }

    @Override
    public List<PlayerDTO> findAllPlayersWithWinRate() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playerDTOs = new ArrayList<>();

        for (Player player : players) {
            PlayerDTO playerDTO = convertToDTO(player);
            float winRate = calculateWinRate(player.getId());
            playerDTO.setWinRate(winRate);
            playerDTOs.add(playerDTO);
        }
        return playerDTOs;
    }
    @Override
    public Double getAverageWinRate() {
        List<Player> players = playerRepository.findAll();
        double totalWinRate = 0.0;
        int playerCount = 0;

        for (Player player : players) {
            List<Game> games = gameRepository.findByPlayerId(player.getId());
            int wins = 0;
            int totalGames = games.size();

            for (Game game : games) {
                if (game.getScore() == 7) {
                    wins++;
                }
            }
            if (totalGames > 0) {
                totalWinRate += (double) wins / totalGames;
                playerCount++;
            }
        }
        if (playerCount > 0) {
            return totalWinRate / playerCount;
        } else {
            return (double) 0;
        }
    }
    @Override
    public PlayerDTO getLoserPlayer() {
        List<Player> players = playerRepository.findAll();
        Player loser = null;
        float lowestWinRate = Float.MAX_VALUE;

        for (Player player : players) {
            float winRate = calculateWinRate(player.getId());

            if (winRate < lowestWinRate) {
                lowestWinRate = winRate;
                loser = player;
            }
        }
        if (loser != null) {
            return convertToDTO(loser);
        } else {
            return null;
        }
    }
    @Override
    public PlayerDTO getWinnerPlayer() {
        List<Player> players = playerRepository.findAll();
        Player winner = null;
        float highestWinRate = Float.MIN_VALUE;

        for (Player player : players) {
            float winRate = calculateWinRate(player.getId());
            if (winRate > highestWinRate) {
                highestWinRate = winRate;
                winner = player;
            }
        }
        if (winner != null) {
            return convertToDTO(winner);
        } else {
            return null;
        }
    }

    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        BeanUtils.copyProperties(player, playerDTO);
        return playerDTO;
    }
    private float calculateWinRate(int playerId) {
        long totalGames = gameService.countGamesByPlayerId(playerId);

        if (totalGames == 0) {
            return 0;
        }
        long gamesWon = gameService.countGamesWonByPlayerId(playerId);

        return (float) gamesWon / totalGames * 100;
    }
}
