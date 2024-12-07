package by.shift.minesweeper;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.BoardHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса {@link BoardHelper}.
 */
class BoardHelperTest {

    private BoardHelper boardHelper;
    private Game game;

    @BeforeEach
    void setUp() {
        boardHelper = new BoardHelper();
        game = new Game("test-game", 10, 10, 10);
    }

    /**
     * Тест проверяет, что все ячейки созданы и не равны null после инициализации игры.
     */
    @Test
    void testInitializeGame_CellsNotNull() {
        boardHelper.initializeGame(game);
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                assertNotNull(game.getCell(row, col), "Cell should not be null");
            }
        }
    }

    /**
     * Тест проверяет, что количество установленных мин соответствует ожидаемому после инициализации игры.
     */
    @Test
    void testInitializeGame_MinesCount() {
        boardHelper.initializeGame(game);
        int mineCount = 0;
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (game.getCell(row, col).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(10, mineCount, "There should be exactly 10 mines on the board");
    }

    /**
     * Тест проверяет, что количество смежных мин вычислено правильно для каждой ячейки после инициализации игры.
     */
    @Test
    void testInitializeGame_AdjacentMinesCalculated() {
        boardHelper.initializeGame(game);
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Cell cell = game.getCell(row, col);
                if (!cell.isMine()) {
                    int expectedAdjacentMines = countExpectedAdjacentMines(game, row, col);
                    assertEquals(expectedAdjacentMines, cell.getAdjacentMines(), "Adjacent mines count should be correct");
                }
            }
        }
    }

    /**
     * Вспомогательный метод для подсчета ожидаемого количества смежных мин для тестов.
     */
    private int countExpectedAdjacentMines(Game game, int row, int col) {
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
     * Вспомогательный метод для проверки, является ли ячейка допустимой для тестов.
     */
    private boolean isValidCell(Game game, int row, int col) {
        return row >= 0 && row < game.getRows() && col >= 0 && col < game.getCols();
    }

    /**
     * Тест проверяет, что ячейка корректно раскрывается, если она не является миной.
     */
    @Test
    void testRevealCell_RevealNonMine() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(false);
        cell.setRevealed(false);
        boardHelper.revealCell(game, 0, 0);
        assertTrue(cell.isRevealed(), "Cell should be revealed");
    }

    /**
     * Тест проверяет, что мина правильно раскрывается и игра заканчивается.
     */
    @Test
    void testRevealCell_RevealMine() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(true);
        cell.setRevealed(false);
        boardHelper.revealCell(game, 0, 0);
        assertTrue(cell.isRevealed(), "Mine cell should be revealed");
        assertTrue(game.isGameOver(), "Game should be over");
    }

    /**
     * Тест проверяет, что смежные ячейки раскрываются, если они пустые.
     */
    @Test
    void testRevealCell_RevealAdjacent() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(false);
        cell.setRevealed(false);
        cell.setAdjacentMines(0);
        boardHelper.revealCell(game, 0, 0);
        assertTrue(cell.isRevealed(), "Cell should be revealed");
        assertTrue(game.getCell(0, 1).isRevealed(), "Adjacent cell should be revealed");
    }

    /**
     * Тест проверяет, что мина не взорвалась, если не была раскрыта.
     */
    @Test
    void testIsMineExploded_MineNotRevealed() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(true);
        cell.setRevealed(false);
        boolean result = boardHelper.isMineExploded(game, 0, 0);
        assertFalse(result, "Mine should not be exploded if not revealed");
    }

    /**
     * Тест проверяет, что мина взорвалась, если была раскрыта.
     */
    @Test
    void testIsMineExploded_MineRevealed() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(true);
        cell.setRevealed(true);
        boolean result = boardHelper.isMineExploded(game, 0, 0);
        assertTrue(result, "Mine should be exploded if revealed");
    }

    /**
     * Тест проверяет, что метод корректно определяет отсутствие мины.
     */
    @Test
    void testIsMineExploded_NoMine() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setMine(false);
        cell.setRevealed(true);
        boolean result = boardHelper.isMineExploded(game, 0, 0);
        assertFalse(result, "Should not be exploded if no mine");
    }

    /**
     * Тест проверяет, что ячейка помечается флагом, и счетчик флагов уменьшается.
     */
    @Test
    void testToggleFlag_SetFlag() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setFlagged(false);
        boardHelper.toggleFlag(game, 0, 0);
        assertTrue(cell.isFlagged(), "Cell should be flagged");
        assertEquals(9, game.getFlagCount(), "Flag count should be decreased");
    }

    /**
     * Тест проверяет, что ячейка снимает флаг, и счетчик флагов увеличивается.
     */
    @Test
    void testToggleFlag_UnsetFlag() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        game.setFlagCount(9);
        cell.setFlagged(true);
        boardHelper.toggleFlag(game, 0, 0);  // Снимаем флаг
        assertFalse(cell.isFlagged(), "Cell should not be flagged");
        assertEquals(10, game.getFlagCount(), "Flag count should be increased");  // Ожидаем увеличение счетчика флагов
    }


    /**
     * Тест проверяет, что флаг не ставится на раскрытую ячейку, и счетчик флагов не изменяется.
     */
    @Test
    void testToggleFlag_AlreadyRevealed() {
        boardHelper.initializeGame(game);
        Cell cell = game.getCell(0, 0);
        cell.setFlagged(false);
        cell.setRevealed(true);
        boardHelper.toggleFlag(game, 0, 0);
        assertFalse(cell.isFlagged(), "Revealed cell should not be flagged");
        assertEquals(10, game.getFlagCount(), "Flag count should not be changed");
    }

    /**
     * Тест проверяет, что игрок выигрывает, если все ячейки, не содержащие мину, были раскрыты.
     */
    @Test
    void testCheckWin_AllCellsRevealed() {
        boardHelper.initializeGame(game);
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Cell cell = game.getCell(row, col);
                if (!cell.isMine()) {
                    cell.setRevealed(true);
                }
            }
        }
        boolean result = boardHelper.checkWin(game);
        assertTrue(result, "Player should win the game");
    }
}
