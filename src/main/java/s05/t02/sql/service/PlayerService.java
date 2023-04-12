package s05.t02.sql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s05.t02.sql.model.Player;
import s05.t02.sql.repository.PlayerRepository;
import java.time.LocalDateTime;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    public Player createPlayer(Player player) {
        if (playerRepository.existsByName(player.getName())) {
            throw new IllegalArgumentException("Player name already exists");
        }
        player.setRegistrationDate(LocalDateTime.now());
        return playerRepository.save(player);
    }
    public Player updatePlayerName(Long id, String name) {
        if (playerRepository.existsByName(name)) {
            throw new IllegalArgumentException("Player name already exists");
        }
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        player.setName(name);
        return playerRepository.save(player);
    }
}
