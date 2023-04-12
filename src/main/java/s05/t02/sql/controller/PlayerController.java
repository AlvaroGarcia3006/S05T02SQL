package s05.t02.sql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import s05.t02.sql.model.Player;
import s05.t02.sql.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        try {
            return ResponseEntity.ok(playerService.createPlayer(player));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayerName(@PathVariable Long id, @RequestBody Player player) {
        try {
            return ResponseEntity.ok(playerService.updatePlayerName(id, player.getName()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
