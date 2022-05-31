package Players;

import Game.*;

public class MiniMax implements IPlayer {
    
    private static final int MAX_DEPTH = 5;

    public MiniMax() {
    }

    @Override
    public int[] makeMove(Game game) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(game.getBoard().getElement(i, j) == Mark.BLANK) {
                    game.setElement(Mark.X, i, j);
                    int moveValue = 0;
                    game.setElement(Mark.BLANK, i, j);
                    if(moveValue > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    public int miniMax(Game game, int depth, boolean maximizingPlayer) {
        // przypisanie wartości aktualnej tablicy
        int boardValue = game.checkWin(Mark.X) ? 10 : -10;
        // sprawdzenie czy nie osiągnięto maksimum głębokości lub końca gry
        if(depth == 0 || Math.abs(boardValue) == 10 || game.checkIfGameFinished()) 
            return boardValue;

        if(maximizingPlayer) {
            int maxEvaluation = Integer.MIN_VALUE;
            for(int i = 0; i < game.getBoardSize(); ++i) {
                for(int j = 0; j < game.getBoardSize(); ++j) {
                    if(game.getBoard().getElement(i, j) == Mark.BLANK) {
                        game.setElement(Mark.X, i, j);
                        maxEvaluation = Math.max(maxEvaluation, 
                            miniMax(game, depth - 1, false));
                        game.setElement(Mark.BLANK, i, j);
                    }
                }
            }
            return maxEvaluation;
        } 
        else {
            int minEvaluation = Integer.MAX_VALUE;
            for(int i = 0; i < game.getBoardSize(); ++i) {
                for(int j = 0; j < game.getBoardSize(); ++j) {
                    if(game.getBoard().getElement(i, j) == Mark.BLANK) {
                        game.setElement(Mark.O, i, j);
                        minEvaluation = Math.max(minEvaluation, 
                            miniMax(game, depth - 1, true));
                        game.setElement(Mark.BLANK, i, j);
                    }
                }
            }
            return minEvaluation;
        }
    }

    public static void main(String[] args) {
        // MiniMax ai = new MiniMax();
    }
}