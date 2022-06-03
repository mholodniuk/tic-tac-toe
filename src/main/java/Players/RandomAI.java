package Players;

import java.util.ArrayList;

import Game.Game;
import Game.Mark;

public class RandomAI{

    private RandomAI() {
    }
    
    public static void makeMove(Game game, Mark mark) {

        ArrayList<int[]> availableMoves = new ArrayList<>();

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }
        int index = (int)(Math.random() * availableMoves.size());
        game.setElement(mark, availableMoves.get(index)[0], availableMoves.get(index)[1]);
    }
}
