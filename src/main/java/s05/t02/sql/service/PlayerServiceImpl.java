package s05.t02.sql.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s05.t02.sql.model.Player;
import s05.t02.sql.model.dto.PlayerDTO;
import s05.t02.sql.repository.PlayerRepository;
import java.time.LocalDateTime;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;

    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        BeanUtils.copyProperties(player, playerDTO);
        return playerDTO;
    }
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
}
