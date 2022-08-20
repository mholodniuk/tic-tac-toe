package Players;

import Game.*;

public class MiniMax {
    
    private static final int MAX_DEPTH = 5;

    public static Integer[] makeMove(Game game) {
        Integer[] bestMove = new Integer[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;
        int depth = 0;
        if(game.getBoardSize() > 3) 
            depth = 3;
        else    
            depth = MAX_DEPTH;

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    game.setElement(Mark.X, i, j);
                    int moveValue = miniMax(game, depth, false);
                    game.changeElement(Mark.BLANK, i, j);
                    if(moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        game.changeElement(Mark.X, bestMove[0], bestMove[1]);
        return bestMove;
    }

    private static int miniMax(Game game, int depth, boolean isMaximizing) {
        int boardValue = 0;
        if(game.checkWin(Mark.X)) {
            boardValue = +1;
        }  
        if(game.checkWin(Mark.O)) {
            boardValue = -1;
        }
        if(Math.abs(boardValue) == 1 || depth == 0 || game.isGameOver()) {
            return boardValue;
        }

        if(isMaximizing) {
            int maxEvaluation = Integer.MIN_VALUE;
            for(int i = 0; i < game.getBoardSize(); i++) {
                for(int j = 0; j < game.getBoardSize(); j++) {
                    if(!game.isPlaceTaken(i, j)) {
                        game.setElement(Mark.X, i, j);
                        int currentMiniMaxValue = miniMax(game, depth - 1, false);
                        game.changeElement(Mark.BLANK, i, j);
                        maxEvaluation = Math.max(maxEvaluation, currentMiniMaxValue);
                    }
                }
            }
            return maxEvaluation;
        } 
        else {
            int minEvaluation = Integer.MAX_VALUE;
            for(int i = 0; i < game.getBoardSize(); i++) {
                for(int j = 0; j < game.getBoardSize(); j++) {
                    if(!game.isPlaceTaken(i, j)) {
                        game.setElement(Mark.O, i, j);
                        game.changeElement(Mark.O, i, j);
                        int currentMiniMaxValue = miniMax(game, depth - 1, true);
                        game.changeElement(Mark.BLANK, i, j);
                        minEvaluation = Math.min(minEvaluation, currentMiniMaxValue);
                    }
                }
            }
            return minEvaluation;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(3, 3);

        MiniMax.makeMove(game);

        game.getBoard().displayBoard();
    }
}