package s05.t02.sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import s05.t02.sql.model.dto.GameDTO;
import s05.t02.sql.model.dto.PlayerDTO;
import s05.t02.sql.service.GameService;
import s05.t02.sql.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        try {
            return ResponseEntity.ok(playerService.createPlayer(playerDTO));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayerName(@PathVariable Integer id, @RequestBody PlayerDTO playerDTO) {
        playerDTO.setId(id);
        try {
            return ResponseEntity.ok(playerService.updatePlayerName(id, playerDTO.getName()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> createGame(@PathVariable("id") int playerId) {
        try {
            return ResponseEntity.ok(gameService.createGame(playerId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}/games")
    public ResponseEntity<GameDTO> deleteGames(@PathVariable("id") int playerId) {
        try {
            gameService.deleteGamesByPlayerId(playerId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> findGames(@PathVariable("id") int playerId) {
        try {
            return ResponseEntity.ok(gameService.findGamesByPlayerId(playerId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAllPlayersWithWinRate() {
        try {
            return ResponseEntity.ok(playerService.findAllPlayersWithWinRate());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/ranking")
    public ResponseEntity<Double> getAverageWinRate() {
        return ResponseEntity.ok(playerService.getAverageWinRate());
    }
}
