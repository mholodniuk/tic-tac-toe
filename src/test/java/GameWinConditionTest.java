
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Game.*;

/**
 * Unit test for simple App.
 */
public class GameWinConditionTest  {

    // 4x3 == size 4x4 && win condition 3 same cells next to each other

    @Test
    public void HorizontalWin4x3Full() {
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

        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 1, 2);
        game.setElement(Mark.X, 1, 3);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void HorizontalWin4x3Part() {
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

        game.setElement(Mark.X, 1, 2);
        game.setElement(Mark.X, 1, 3);
        game.setElement(Mark.X, 1, 4);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void VerticalWin4x3Full() {
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

        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 1);
        game.setElement(Mark.X, 3, 1);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void VerticalWin4x3Part() {
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

        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 1);
        game.setElement(Mark.X, 4, 1);

        assertFalse(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalBackSlashWin4x3Part1() {
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

        game.setElement(Mark.X, 2, 1);
        game.setElement(Mark.X, 3, 2);
        game.setElement(Mark.X, 4, 3);

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

        game.setElement(Mark.X, 1, 2);
        game.setElement(Mark.X, 2, 3);
        game.setElement(Mark.X, 3, 4);

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

        game.setElement(Mark.X, 1, 1);
        game.setElement(Mark.X, 2, 2);
        game.setElement(Mark.X, 3, 3);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin4x3Full() {
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

        game.setElement(Mark.X, 1, 4);
        game.setElement(Mark.X, 2, 3);
        game.setElement(Mark.X, 3, 2);
        game.setElement(Mark.X, 4, 1);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin4x3Part1() {
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

        game.setElement(Mark.X, 1, 3);
        game.setElement(Mark.X, 2, 2);
        game.setElement(Mark.X, 3, 1);

        assertTrue(game.checkWin(Mark.X));
    }

    @Test
    public void DiagonalSlashWin4x3Part2() {
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

        game.setElement(Mark.X, 2, 4);
        game.setElement(Mark.X, 3, 3);
        game.setElement(Mark.X, 4, 2);

        assertTrue(game.checkWin(Mark.X));
    }
}
