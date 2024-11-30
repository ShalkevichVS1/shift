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
        boardHelper.printBoard(game); // Вызов метода printBoard для вывода игрового поля
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
            if (boardHelper.revealCell(game, row, col)) {
                game.setGameOver(true);
            }
            if (checkWin(game)) {
                showWinMessage();
                game.setGameOver(true);
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
            if (checkWin(game)) {
                showWinMessage();
                game.setGameOver(true);
            }
        }
        return game;
    }

    /**
     * Проверяет, выполнены ли условия выигрыша.
     *
     * @param game объект Game
     * @return true, если выполнены условия выигрыша; иначе false
     */
    private boolean checkWin(Game game) {
        // Условия выигрыша: все клетки, кроме мин, раскрыты или все мины помечены флагами
        boolean allCellsRevealed = true;
        boolean allMinesFlagged = true;
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (!game.getCell(row, col).isRevealed() && !game.getCell(row, col).isMine()) {
                    allCellsRevealed = false;
                }
                if (game.getCell(row, col).isMine() && !game.getCell(row, col).isFlagged()) {
                    allMinesFlagged = false;
                }
            }
        }
        return allCellsRevealed || allMinesFlagged;
    }

    /**
     * Отображает сообщение о выигрыше.
     */
    private void showWinMessage() {
        System.out.println("Поздравляем! Вы выиграли!");
    }
}
