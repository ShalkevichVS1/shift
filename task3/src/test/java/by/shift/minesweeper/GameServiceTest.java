package by.shift.minesweeper;

import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;
    private Game game;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
        game = gameService.createNewGame(5, 5, 5); // Инициализируем игру с 5 строками, 5 колонками и 5 минами
    }

    @Test
    void testCreateNewGame() {
        assertNotNull(game);
        assertEquals(5, game.getRows());
        assertEquals(5, game.getCols());
        assertEquals(5, game.getMinesCount());
        assertFalse(game.isGameOver());
        assertFalse(game.isWin());
    }

    @Test
    public void testRevealCell_NoMine() {
        Game game = gameService.createNewGame(10, 10, 10);
        UUID gameId = UUID.fromString(game.getId());

        // Найдите ячейку, которая не содержит мин
        int row = 0;
        int col = 0;
        while (game.getCell(row, col).isMine()) {
            row++;
            if (row == game.getRows()) {
                row = 0;
                col++;
            }
        }

        game = gameService.revealCell(gameId, row, col);

        // Убедитесь, что ячейка раскрыта и не содержит мин
        assertFalse(game.getCell(row, col).isMine());
        assertTrue(game.getCell(row, col).isRevealed());
    }


    @Test
    void testRevealCell_Mine() {
        // Для теста, заставим ячейку (0, 1) быть миной
        game.getCell(0, 1).setMine(true);

        // Попробуем раскрыть эту ячейку
        Game updatedGame = gameService.revealCell(UUID.fromString(game.getId()), 0, 1);

        assertNotNull(updatedGame);
        assertTrue(updatedGame.getCell(0, 1).isRevealed());
        assertTrue(updatedGame.isGameOver());
    }

    @Test
    void testToggleFlag() {
        // Переключаем флаг на ячейке (0, 0)
        Game updatedGame = gameService.toggleFlag(UUID.fromString(game.getId()), 0, 0);

        assertNotNull(updatedGame);
        assertTrue(updatedGame.getCell(0, 0).isFlagged());
        assertEquals(4, updatedGame.getFlagCount());  // После установки флага, флагов остается 4

        // Снимаем флаг с ячейки (0, 0)
        updatedGame = gameService.toggleFlag(UUID.fromString(game.getId()), 0, 0);

        assertFalse(updatedGame.getCell(0, 0).isFlagged());
        assertEquals(5, updatedGame.getFlagCount());  // После снятия флага, флагов остается 5
    }

    @Test
    void testCheckWin_WithoutWin() {
        // Убедимся, что при обычной игре победы нет
        Game updatedGame = gameService.revealCell(UUID.fromString(game.getId()), 0, 0);
        assertFalse(updatedGame.isWin());
    }

    @Test
    void testCheckWin_WithWin() {
        // Сделаем все ячейки, кроме мин, раскрытыми, чтобы проверить победу
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (!game.getCell(row, col).isMine()) {
                    game.getCell(row, col).setRevealed(true);
                }
            }
        }

        Game updatedGame = gameService.revealCell(UUID.fromString(game.getId()), 0, 0);
        assertTrue(updatedGame.isWin());
        assertTrue(updatedGame.isGameOver());
    }

    @Test
    void testGameOverMessage() {
        // Проверяем, что сообщение о конце игры появляется при проигрыше
        game.getCell(0, 0).setMine(true);
        gameService.revealCell(UUID.fromString(game.getId()), 0, 0);
        // Мы не можем проверить вывод в консоль непосредственно, но можно проверить статус игры
        assertTrue(game.isGameOver());
    }

    @Test
    void testWinMessage() {
        // Убедимся, что сообщение о победе появляется при выигрыше
        // Например, раскрываем все ячейки без мин
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (!game.getCell(row, col).isMine()) {
                    game.getCell(row, col).setRevealed(true);
                }
            }
        }

        // Дополнительно раскрываем все ячейки, чтобы подтвердить, что тест работает правильно
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (!game.getCell(row, col).isMine()) {
                    gameService.revealCell(UUID.fromString(game.getId()), row, col);
                }
            }
        }

        // Теперь проверяем, вызовет ли этот вызов состояния победы
        Game updatedGame = gameService.revealCell(UUID.fromString(game.getId()), 0, 0);

        // Проверяем, что игра выиграна
        assertTrue(updatedGame.isWin(), "Игра должна быть выиграна");
        assertTrue(updatedGame.isGameOver(), "Игра должна быть завершена");
    }


}

