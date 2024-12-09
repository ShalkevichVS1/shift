package by.shift.minesweeper.model;

import lombok.Data;

@Data
public class Game {
    private final String id; // Уникальный идентификатор игры
    private final int rows; // Количество строк на игровом поле
    private final int cols; // Количество колонок на игровом поле
    private final int minesCount; // Общее количество мин на игровом поле
    private int flagCount; // Количество оставшихся флагов
    private int flaggedMines; // Количество правильно помеченных флагами мин
    private final Cell[][] board; // Игровое поле в виде двумерного массива ячеек
    private boolean gameOver; // Статус игры, показывающий, завершена ли игра
    private boolean firstMoveMade; // Статус, показывающий, был ли совершен первый ход
    private boolean win; // Поле для определения победы

    public Game(String id, int rows, int cols, int minesCount) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.minesCount = minesCount;
        this.board = new Cell[rows][cols];
        this.gameOver = false;
        this.flaggedMines = 0;
        this.flagCount = minesCount;
        this.firstMoveMade = false;
        this.win = false;

    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        board[row][col] = cell;
    }

}
