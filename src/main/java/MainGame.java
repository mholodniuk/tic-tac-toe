import Game.*;
import Players.*;

public class MainGame {

    public static void main(String[] args) {
        Game tictactoe = new Game(4, 3);

        int counter = 0;
        int winner = 0;
        tictactoe.getBoard().displayBoard();

        while(!tictactoe.isGameOver()) {
            if(counter % 2 == 0) {
                Player.makeMove(tictactoe, Mark.O);

                if(tictactoe.checkWin(Mark.O)) {
                    System.out.println("O's win!");
                    winner++;
                    break;
                }
            }
            else {
                MiniMax.makeMove(tictactoe, Mark.X);
                // RandomAI.makeMove(tictactoe, Mark.X);

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