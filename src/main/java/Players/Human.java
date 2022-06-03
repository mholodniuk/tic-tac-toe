package Players;

import java.util.Scanner;

import Game.Game;
import Game.Mark;

public class Human {
    
    public int[] makeMove(Game game, Mark playerMark) {

        Scanner in = new Scanner(System.in);
        int row = -1, col = -1;

        System.out.print(playerMark.toString() + "'s move. Choose place to mark: (eg. 1 2): ");
        row = in.nextInt();
        col = in.nextInt();

        while(!game.setElement(playerMark, row, col)) {
            System.out.print("Bad choice. Choose another place to mark: (eg. 1 2): ");
            row = in.nextInt();
            col = in.nextInt();
        }
        // in.close();
        return new int[] {row, col};
    }
}
