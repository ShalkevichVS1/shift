package by.shift.minesweeper.service;

import by.shift.minesweeper.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GameService отвечает за управление игровыми сессиями Сапёра.
 */
@Service
@Slf4j
public class GameService {
    private final Map<UUID, Game> games = new ConcurrentHashMap<>();
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
        if (log.isDebugEnabled()) {
            log.info("Game initialized: ");
            boardHelper.printBoard(game);
        }
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
        log.info("Поздравляем! Вы выиграли!");
    }

    /**
     * Отображает сообщение о проигрыше.
     */
    public void showGameOverMessage() {
        log.info("Вы проиграли! Попробуйте ещё раз!");
    }
}


