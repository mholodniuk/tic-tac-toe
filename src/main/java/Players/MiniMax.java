package Players;

import Game.*;

public class MiniMax implements IPlayer {
    
    private static final int MAX_DEPTH = 100;

    public MiniMax() {
    }

    @Override
    public int[] makeMove(Game game) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        Game aGame = new Game(game);

        for(int i = 0; i < aGame.getBoardSize(); ++i) {
            // System.out.println("wiersz");
            for(int j = 0; j < aGame.getBoardSize(); ++j) {
                // System.out.println("kolumna");
                if(aGame.getBoard().getElement(i, j) == Mark.BLANK) {
                    aGame.setElement(Mark.X, i, j);
                    int moveValue = miniMax(aGame, MAX_DEPTH, false);
                    // System.out.println("minimax for: (" + i + " " + j + ") = " + moveValue);
                    aGame.setElement(Mark.BLANK, i, j);
                    if(moveValue > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        // System.out.println("best move = (" + bestMove[0] + " " + bestMove[1] + ")");
        return bestMove;
    }

    public int miniMax(Game game, int depth, boolean maximizingPlayer) {
        // przypisanie wartości aktualnej tablicy
        int boardValue = 0;
        if(game.checkWin(Mark.X))
            boardValue = 10;
        if(game.checkWin(Mark.O))
            boardValue = -10;

        Game aGame = new Game(game);

        // System.out.println(boardValue);
        // sprawdzenie czy nie osiągnięto maksimum głębokości lub końca gry
        if(depth == 0 || Math.abs(boardValue) == 10 || !aGame.checkIfAnyMovesAvailable()) 
            return boardValue;

        if(maximizingPlayer) {
            // System.out.println("maximizing player");
            int maxEvaluation = Integer.MIN_VALUE;
            for(int i = 0; i < aGame.getBoardSize(); ++i) {
                for(int j = 0; j < aGame.getBoardSize(); ++j) {
                    if(aGame.getBoard().getElement(i, j) == Mark.BLANK) {
                        aGame.setElement(Mark.X, i, j);
                        maxEvaluation = Math.max(maxEvaluation, 
                            miniMax(aGame, depth - 1, false));
                        aGame.setElement(Mark.BLANK, i, j);
                    }
                }
            }
            return maxEvaluation;
        } 
        else {
            // System.out.println("minimizing player");
            int minEvaluation = Integer.MAX_VALUE;
            for(int i = 0; i < aGame.getBoardSize(); ++i) {
                for(int j = 0; j < aGame.getBoardSize(); ++j) {
                    if(aGame.getBoard().getElement(i, j) == Mark.BLANK) {
                        aGame.setElement(Mark.O, i, j);
                        minEvaluation = Math.min(minEvaluation, 
                            miniMax(aGame, depth - 1, true));
                        aGame.setElement(Mark.BLANK, i, j);
                    }
                }
            }
            return minEvaluation;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(3, 3);
        MiniMax AI = new MiniMax();
        // Human human = new Human();
        int[] result;

        game.getBoard().displayBoard();

        // ruch gracza
        game.setElement(Mark.O, 0, 0);
        // game.getBoard().displayBoard();

        // ruch gracza
        game.setElement(Mark.O, 1, 1);
        game.getBoard().displayBoard();

        // ruch minimaxa
        result = AI.makeMove(game);
        game.setElement(Mark.X, result[0], result[1]);
        game.getBoard().displayBoard();

        
        // ruch minimaxa
        result = AI.makeMove(game);
        game.setElement(Mark.X, result[0], result[1]);
        game.getBoard().displayBoard();

        // ruch minimaxa
        result = AI.makeMove(game);
        game.setElement(Mark.X, result[0], result[1]);
        game.getBoard().displayBoard();

    }
}