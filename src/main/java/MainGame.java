import Game.*;
import Players.*;

public class MainGame {

    public static void main(String[] args) {
        Game tictactoe = new Game(3, 3);

        Human humanPlayer = new Human();
        // MiniMax AIPlayer = new MiniMax();
        // RandomAI AIRandomPlayer = new RandomAI();
        int counter = 0;
        int winner = 0;
        int[] result = null;
        tictactoe.getBoard().displayBoard();

        while(!tictactoe.isGameOver()) {
            // tictactoe.getBoard().displayBoard();
            if(counter % 2 != 0) {
                humanPlayer.makeMove(tictactoe, Mark.O);

                if(tictactoe.checkWin(Mark.O)) {
                    System.out.println("O's win!");
                    winner++;
                    break;
                }
            }
            else {
                // result = AIRandomPlayer.makeMove(tictactoe, Mark.X);
                result = MiniMax.makeMove(tictactoe, Mark.X);
                tictactoe.setElement(Mark.X, result[0], result[1]);
                // AIPlayer.makeMove(tictactoe, Mark.X);

                if(tictactoe.checkWin(Mark.X)) {
                    System.out.println("X's win!");
                    winner++;
                    break;
                }
            }
            counter++;
            tictactoe.getBoard().displayBoard();
        }
        tictactoe.getBoard().displayBoard();
        if(tictactoe.isGameOver() && winner == 0)
            System.out.println("Out of moves, tie!");
    }
}