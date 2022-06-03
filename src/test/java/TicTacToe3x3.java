import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Game.*;

/**
 * Unit test for simple App.
 */
public class TicTacToe3x3  {

    // 3x2 == size 3x3 && win condition 2 same cells next to each other

    @Test
    public void VerticalWin() {
        Game game = new Game(3, 3);

        game.setElement(Mark.O, 0, 0);
        game.setElement(Mark.O, 0, 1);
        game.setElement(Mark.O, 0, 2);

        assertTrue(game.checkWin(Mark.O));
    }

    @Test
    public void HorizotnalWin() {
        Game game = new Game(3, 3);


        game.setElement(Mark.O, 0, 1);
        game.setElement(Mark.O, 1, 1);
        game.setElement(Mark.O, 2, 1);

        assertTrue(game.checkWin(Mark.O));
    }
}