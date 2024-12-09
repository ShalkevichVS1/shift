package by.shift.minesweeper.controller;

import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public Game startNewGame() {
        return gameService.createNewGame(10, 10, 10);
    }

    @PostMapping("/game/{id}/reveal")
    public Game revealCell(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        return gameService.revealCell(UUID.fromString(id), row, col);
    }

    @PostMapping("/game/{id}/toggle-flag")
    public Game toggleFlag(@PathVariable("id") String id, @RequestParam int row, @RequestParam int col) {
        return gameService.toggleFlag(UUID.fromString(id), row, col);
    }
}
