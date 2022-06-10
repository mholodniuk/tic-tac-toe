import java.util.Scanner;

import Game.*;
import Players.*;

public class ConsoleGame {

    public static void main(String[] args) {

        int size = 0, winCondition = 0;
        try {
            Scanner in = new Scanner(System.in);

            System.out.print("Enter size of your board: ");
            size = in.nextInt();
            System.out.print("Enter win condition (must be equal or smaller than your board size): ");
            winCondition = in.nextInt();
            // in.close();

        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        Game tictactoe = new Game(size, winCondition);

        int counter = 0;
        int winner = 0;
        // tictactoe.getBoard().displayBoard();

        while(!tictactoe.isGameOver()) {
            tictactoe.getBoard().displayBoard();

            if(counter % 2 == 0) {
                Player.makeMove(tictactoe);

                if(tictactoe.checkWin(Mark.O)) {
                    System.out.println("O's win!");
                    winner++;
                    break;
                }
            }
            else {
                // MiniMax.makeMove(tictactoe);
                System.out.println("X's move. AI makes a decision");
                MiniMaxAlphaBeta.makeMove(tictactoe);
                // RandomAI.makeMove(tictactoe);

                if(tictactoe.checkWin(Mark.X)) {
                    System.out.println("X's win!");
                    winner++;
                    break;
                }
            }
            counter++;
            // tictactoe.getBoard().displayBoard();
        }
        tictactoe.getBoard().displayBoard();
        if(tictactoe.isGameOver() && winner == 0)
            System.out.println("Out of moves, tie!");
    }
}