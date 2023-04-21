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
    @ResponseBody
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
            return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PlayerDTO> updatePlayerName(@PathVariable Integer id, @RequestBody PlayerDTO playerDTO) {
        playerDTO.setId(id);
            return ResponseEntity.ok(playerService.updatePlayerName(id, playerDTO.getName()));
    }
    @PostMapping("/{id}/games")
    @ResponseBody
    public ResponseEntity<GameDTO> createGame(@PathVariable("id") int playerId) {
            return ResponseEntity.ok(gameService.createGame(playerId));
    }
    @DeleteMapping("/{id}/games")
    @ResponseBody
    public ResponseEntity<Void> deleteGames(@PathVariable("id") int playerId) {
            gameService.deleteGamesByPlayerId(playerId);
            return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/games")
    @ResponseBody
    public ResponseEntity<List<GameDTO>> findGames(@PathVariable("id") int playerId) {
            return ResponseEntity.ok(gameService.findGamesByPlayerId(playerId));
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PlayerDTO>> findAllPlayersWithWinRate() {
            return ResponseEntity.ok(playerService.findAllPlayersWithWinRate());
    }
    @GetMapping("/ranking")
    @ResponseBody
    public ResponseEntity<Double> getAverageWinRate() {
        return ResponseEntity.ok(playerService.getAverageWinRate());
    }
    @GetMapping("/ranking/loser")
    @ResponseBody
    public ResponseEntity<PlayerDTO> getLoserPlayer() {
        return ResponseEntity.ok(playerService.getLoserPlayer());
    }

    @GetMapping("/ranking/winner")
    @ResponseBody
    public ResponseEntity<PlayerDTO> getWinnerPlayer() {
        return ResponseEntity.ok(playerService.getWinnerPlayer());
    }
}
