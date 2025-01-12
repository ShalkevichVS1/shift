package by.shift.minesweeper.model;

import lombok.Data;

@Data
public class Cell {
    private boolean mine; // Указывает, содержит ли ячейка мину
    private boolean revealed; // Указывает, была ли ячейка раскрыта
    private boolean flagged; // Указывает, была ли ячейка помечена флагом
    private int adjacentMines; // Количество мин, смежных с этой ячейкой
}
