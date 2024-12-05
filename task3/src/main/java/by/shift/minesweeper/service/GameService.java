package by.shift.minesweeper.service;

import by.shift.minesweeper.model.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * GameService отвечает за управление игровыми сессиями Сапёра.
 */
@Service
public class GameService {
    private final Map<UUID, Game> games = new HashMap<>();
    private final BoardHelper boardHelper = new BoardHelper();

    /**
     * Создает новую игру Сапёр с указанными параметрами.
     *
     * @param rows количество строк на игровом поле
     * @param cols количество колонок на игровом поле
     * @param minesCount количество мин на игровом поле
     * @return объект Game, представляющий созданную игру
     */
    public Game createNewGame(int rows, int cols, int minesCount) {
        UUID gameId = UUID.randomUUID();
        Game game = new Game(gameId.toString(), rows, cols, minesCount);
        boardHelper.initializeGame(game);
        System.out.println("Game initialized: ");
        boardHelper.printBoard(game);
        games.put(gameId, game);
        return game;
    }

    /**
     * Раскрывает ячейку на игровом поле.
     *
     * @param gameId идентификатор игры
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     * @return обновленный объект Game
     */
    public Game revealCell(UUID gameId, int row, int col) {
        Game game = games.get(gameId);
        if (game != null && !game.isGameOver()) {
            boardHelper.revealCell(game, row, col);
            if (boardHelper.isMineExploded(game, row, col)) {
                game.setGameOver(true);
                showGameOverMessage();
            } else if (boardHelper.checkWin(game)) {
                game.setGameOver(true);
                game.setWin(true);
                showWinMessage();
            }
        }
        return game;
    }

    /**
     * Переключает флаг на ячейке игрового поля.
     *
     * @param gameId идентификатор игры
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     * @return обновленный объект Game
     */
    public Game toggleFlag(UUID gameId, int row, int col) {
        Game game = games.get(gameId);
        if (game != null && !game.isGameOver()) {
            boardHelper.toggleFlag(game, row, col);
            if (boardHelper.checkWin(game)) {
                game.setGameOver(true);
                game.setWin(true);
                showWinMessage();
            }
        }
        return game;
    }

    /**
     * Отображает сообщение о выигрыше.
     */
    public void showWinMessage() {
        System.out.println("Поздравляем! Вы выиграли!");
    }

    /**
     * Отображает сообщение о проигрыше.
     */
    public void showGameOverMessage() {
        System.out.println("Вы проиграли! Попробуйте ещё раз!");
    }
}


