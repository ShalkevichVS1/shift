package by.shift.minesweeper.controller;

import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.UUID;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public ResponseEntity<Game> startNewGame() {
        Game game = gameService.createNewGame(10, 10, 10);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/game/{id}/reveal")
    public ResponseEntity<Game> revealCell(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        Game game = gameService.revealCell(UUID.fromString(id), row, col);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/game/{id}/toggle-flag")
    public ResponseEntity<Game> toggleFlag(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        Game game = gameService.toggleFlag(UUID.fromString(id), row, col);
        return ResponseEntity.ok(game);
    }
}
