package Players;

import java.util.Scanner;

import Game.Game;
import Game.Mark;

public class Human implements IPlayer {
    
    public int[] makeMove(Game game) {

        Scanner in = new Scanner(System.in);
        int row = -1, col = -1;

        System.out.print("Podaj wiersz i kolumnę (np. 1 2): ");
        row = in.nextInt();
        col = in.nextInt();

        while(!game.setElement(Mark.O, row, col)) {
            System.out.print("Złe dane. Podaj wiersz i kolumnę (np. 1 2): ");
            row = in.nextInt();
            col = in.nextInt();
        }
        in.close();
        return new int[] {row, col};
    }
}
