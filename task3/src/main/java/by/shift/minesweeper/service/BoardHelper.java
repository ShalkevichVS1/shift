package by.shift.minesweeper.service;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardHelper содержит вспомогательные методы для управления игровым полем Сапёра.
 */
@Slf4j
public class BoardHelper {

    /**
     * Инициализирует игровое поле, заполняет его ячейками и устанавливает мины.
     *
     * @param game объект Game для инициализации
     */
    public void initializeGame(Game game) {
        int rows = game.getRows();
        int cols = game.getCols();
        int minesCount = game.getMinesCount();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                game.setCell(row, col, new Cell());
            }
        }

        placeMines(game, rows, cols, minesCount);
        calculateAdjacentMines(game, rows, cols);
    }

    /**
     * Раскрывает ячейку.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     */
    public void revealCell(Game game, int row, int col) {
        Cell cell = game.getCell(row, col);
        if (!cell.isRevealed() && !cell.isFlagged()) {
            cell.setRevealed(true);
            game.setFirstMoveMade(true);
            if (cell.isMine()) {
                game.setGameOver(true);
            } else if (cell.getAdjacentMines() == 0) {
                revealAdjacentCells(game, row, col);
            }
        }
    }

    /**
     * Проверяет, взорвана ли мина.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     * @return true, если мина взорвана; иначе false
     */
    public boolean isMineExploded(Game game, int row, int col) {
        Cell cell = game.getCell(row, col);
        return cell.isMine() && cell.isRevealed();
    }

    /**
     * Переключает флаг на ячейке.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     */
    public void toggleFlag(Game game, int row, int col) {
        Cell cell = game.getCell(row, col);
        if (!cell.isRevealed()) {
            if (cell.isFlagged()) {
                cell.setFlagged(false); // Снимаем флаг
                game.setFlagCount(game.getFlagCount() + 1); // Увеличиваем количество флагов
            } else if (game.getFlagCount() > 0) {
                cell.setFlagged(true); // Ставим флаг
                game.setFlagCount(game.getFlagCount() - 1); // Уменьшаем количество флагов
            }
        }
    }





    /**
     * Расставляет мины на игровом поле.
     *
     * @param game объект Game
     * @param rows количество строк на игровом поле
     * @param cols количество колонок на игровом поле
     * @param minesCount количество мин на игровом поле
     */
    private void placeMines(Game game, int rows, int cols, int minesCount) {
        int placedMines = 0;
        while (placedMines < minesCount) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);
            if (!game.getCell(row, col).isMine()) {
                game.getCell(row, col).setMine(true);
                placedMines++;
            }
        }
    }

    /**
     * Подсчитывает количество мин вокруг каждой ячейки.
     *
     * @param game объект Game
     * @param rows количество строк на игровом поле
     * @param cols количество колонок на игровом поле
     */
    private void calculateAdjacentMines(Game game, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!game.getCell(row, col).isMine()) {
                    int adjacentMines = countAdjacentMines(game, row, col);
                    game.getCell(row, col).setAdjacentMines(adjacentMines);
                }
            }
        }
    }

    /**
     * Подсчитывает количество мин вокруг заданной ячейки.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     * @return количество мин вокруг ячейки
     */
    private int countAdjacentMines(Game game, int row, int col) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                int newRow = row + dr;
                int newCol = col + dc;
                if (isValidCell(game, newRow, newCol) && game.getCell(newRow, newCol).isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Раскрывает смежные ячейки, если они не содержат мины.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     */
    private void revealAdjacentCells(Game game, int row, int col) {
        int[] directions = {-1, 0, 1};
        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue;
                int newRow = row + dr;
                int newCol = col + dc;
                if (isValidCell(game, newRow, newCol) &&
                        !game.getCell(newRow, newCol).isRevealed() &&
                        !game.getCell(newRow, newCol).isFlagged()) {
                    game.getCell(newRow, newCol).setRevealed(true);
                    if (game.getCell(newRow, newCol).getAdjacentMines() == 0) {
                        revealAdjacentCells(game, newRow, newCol);
                    }
                }
            }
        }
    }

    /**
     * Проверяет, является ли заданная ячейка допустимой.
     *
     * @param game объект Game
     * @param row номер строки ячейки
     * @param col номер колонки ячейки
     * @return true, если ячейка допустима; иначе false
     */
    private boolean isValidCell(Game game, int row, int col) {
        return row >= 0 && row < game.getRows() && col >= 0 && col < game.getCols();
    }

    /**
     * Проверяет, выиграл ли игрок.
     *
     * @param game объект Game
     * @return true, если игрок выиграл; иначе false
     */
    public boolean checkWin(Game game) {
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Cell cell = game.getCell(row, col);
                if (cell.isMine() && !cell.isFlagged()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Печатает текущее состояние игрового поля в консоль.
     *
     * @param game объект Game для печати
     */
    void printBoard(Game game) {
        int rows = game.getRows();
        int cols = game.getCols();
        log.debug("---------- Игровое поле ----------");
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = game.getCell(row, col);
                if (cell.isMine()) {
                    stringBuilder.append(String.format("%-2s", "X"));
                } else if (cell.isRevealed()) {
                    stringBuilder.append(String.format("%-2d", cell.getAdjacentMines()));
                } else {
                    stringBuilder.append(String.format("%-2s", "."));
                }
            }
            stringBuilder.append("\n");
        }
        log.info("\n" + stringBuilder);
        log.info("----------------------------------");
    }
}
