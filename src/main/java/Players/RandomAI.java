package Players;

import java.util.ArrayList;

import Game.Game;
import Game.Mark;

public class RandomAI{

    private RandomAI() {
    }
    
    public int[] makeMove(Game game, Mark mark) {

        ArrayList<int[]> availableMoves = new ArrayList<>();

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }
        int index = (int)(Math.random() * availableMoves.size());
        return availableMoves.get(index);
    }
}
