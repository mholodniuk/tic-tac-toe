package Players;

import java.util.Scanner;

import Game.Game;
import Game.Mark;

public class Player {
    
    public static void makeMove(Game game) {
        Scanner in = new Scanner(System.in);
        int row = -1, col = -1;

        System.out.print("O's move. Choose place to mark: (eg. 1 2): ");
        row = in.nextInt();
        col = in.nextInt();

        while(!game.setElement(Mark.O, row, col)) {
            System.out.print("Bad choice. Choose another place to mark: (eg. 1 2): ");
            row = in.nextInt();
            col = in.nextInt();
        }
        // in.close();
    }
}
