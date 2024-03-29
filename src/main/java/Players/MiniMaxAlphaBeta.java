package Players;

import Game.Game;
import Game.Mark;

public class MiniMaxAlphaBeta {

    private static final int MAX_DEPTH = 12;

    public static Integer[] makeMove(Game game) {
        Integer[] bestMove = new Integer[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;
        int depth = 0;

        if(game.getBoardSize() > 3 && game.getBoardSize() <= 5)
            depth = 6;
        else if(game.getBoardSize() <= 3) 
            depth = MAX_DEPTH;
        else 
            depth = 3;

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    game.setElement(Mark.X, i, j);
                    int moveValue = miniMax(game, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, false);
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

    private static int miniMax(Game game, int alpha, int beta, int depth, boolean isMaximizing) {
        int boardValue = 0;
        if(game.checkWin(Mark.X)) {
            boardValue = 1;
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
                        int currentMiniMaxValue = miniMax(game, alpha, beta, depth - 1, false);
                        game.changeElement(Mark.BLANK, i, j);
                        maxEvaluation = Math.max(maxEvaluation, currentMiniMaxValue);
                        alpha = Math.max(alpha, maxEvaluation);
                        if(beta <= alpha)
                            return maxEvaluation;
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
                        int currentMiniMaxValue = miniMax(game, alpha, beta, depth - 1, true);
                        game.changeElement(Mark.BLANK, i, j);
                        minEvaluation = Math.min(minEvaluation, currentMiniMaxValue);
                        beta = Math.min(beta, currentMiniMaxValue);
                        if(beta <= alpha)
                            return minEvaluation;
                    }
                }
            }
            return minEvaluation;
        }
    }
}
