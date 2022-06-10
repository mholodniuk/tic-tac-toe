
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Game.*;

/**
 * Unit test for simple App.
 */
public class GameWinConditionTest  {

    // 3x2 == size 3x3 && win condition 2 same cells next to each other

    @Test
    public void HorizontalWin4x3Part1() {
        Game game = new Game(4, 3);

        // -----------------
        // | X | X | X |   |
        // -----------------
        // |   |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 0);
        game.setElement(Mark.X, 0, 1);
        game.setElement(Mark.X, 0, 2);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void HorizontalWin4x3Part2() {
        Game game = new Game(4, 3);

        // -----------------
        // |   | X | X | X |
        // -----------------
        // |   |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 1);
        game.setElement(Mark.X, 0, 2);
        game.setElement(Mark.X, 0, 3);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void VerticalWin4x3Part1() {
        Game game = new Game(4, 3);

        // -----------------
        // | X |   |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 0);
        game.setElement(Mark.X, 1, 0);
        game.setElement(Mark.X, 2, 0);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void VerticalWin4x3Part2() {
        Game game = new Game(4, 3);

        // -----------------
        // | X |   |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 0);
        game.setElement(Mark.X, 1, 0);
        game.setElement(Mark.X, 3, 0);

        assertFalse(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalBackSlashWin3x2Part0() {
        Game game = new Game(4, 3);

        // -----------------
        // |   |   |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------
        // |   | X |   |   |
        // -----------------
        // |   |   | X |   |
        // -----------------

        game.setElement(Mark.X, 1, 0);
        game.setElement(Mark.X, 2, 1);
        game.setElement(Mark.X, 3, 2);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalBackSlashWin3x2Part1() {
        Game game = new Game(4, 3);

        // -----------------
        // |   | X |   |   |
        // -----------------
        // |   |   | X |   |
        // -----------------
        // |   |   |   | X |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 1);
        game.setElement(Mark.X, 1, 2);
        game.setElement(Mark.X, 2, 3);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalBackSlashWin4x3Part2() {
        Game game = new Game(4, 3);

        // -----------------
        // |   | X |   |   |
        // -----------------
        // |   |   | X |   |
        // -----------------
        // |   |   |   | X |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 0);
        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 2);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalBackSlashWin3x3Full() {
        Game game = new Game(3, 3);

        // -------------
        // | X |   |   |
        // -------------
        // |   | X |   |
        // -------------
        // |   |   | X |
        // -------------

        game.setElement(Mark.X, 0, 0);
        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 2);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin4x4Full() {
        Game game = new Game(4, 3);

        // -----------------
        // |   |   |   | X |
        // -----------------
        // |   |   | X |   |
        // -----------------
        // |   | X |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 3);
        game.setElement(Mark.X, 1, 2);
        game.setElement(Mark.X, 2, 1);
        game.setElement(Mark.X, 3, 0);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin4x3Part0() {
        Game game = new Game(4, 3);

        // -----------------
        // |   |   | X |   |
        // -----------------
        // |   | X |   |   |
        // -----------------
        // | X |   |   |   |
        // -----------------
        // |   |   |   |   |
        // -----------------

        game.setElement(Mark.X, 0, 2);
        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 0);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin3x2Part1() {
        Game game = new Game(4, 3);

        // -----------------
        // |   |   |   |   |
        // -----------------
        // |   |   |   | X |
        // -----------------
        // |   |   | X |   |
        // -----------------
        // |   | X |   |   |
        // -----------------

        game.setElement(Mark.X, 1, 3);
        game.setElement(Mark.X, 2, 2);
        game.setElement(Mark.X, 3, 1);

        assertTrue(game.checkWin(Mark.X));
    }
}
