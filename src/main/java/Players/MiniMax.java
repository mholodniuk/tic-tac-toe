package Players;

import Game.*;

public class MiniMax{
    
    private static final int MAX_DEPTH = 1000;

    private MiniMax() {
    }

    public static int[] makeMove(Game game, Mark mark) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        Game gameCopy = new Game(game);

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    gameCopy.setElement(Mark.X, i, j);
                    int moveValue = miniMax(gameCopy, MAX_DEPTH, false);
                    // System.out.println("minimax for: (" + i + " " + j + ") = " + moveValue);
                    gameCopy.setElement(Mark.BLANK, i, j);
                    if(moveValue > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        System.out.println("best move = (" + bestMove[0] + " " + bestMove[1] + ")");
        return bestMove;
    }

    public static int miniMax(Game game, int depth, boolean isMaximizing) {
        // zwrócenie wartości aktualnej tablicy jeśli koniec gry
        int boardValue = 0;
        if(game.checkWin(Mark.X)) {
            boardValue = +1;
        }  
        if(game.checkWin(Mark.O)) {
            boardValue = -1;
        }

        // sprawdzenie czy nie osiągnięto maksimum głębokości
        if(Math.abs(boardValue) == 1 || depth == 0 || game.isGameOver()) {
            // game.getBoard().displayBoard();
            // System.out.println(boardValue);
            return boardValue;
        }

        // Game gameCopy = new Game(game);

        if(isMaximizing) {
            int maxEvaluation = Integer.MIN_VALUE;
            for(int i = 0; i < game.getBoardSize(); i++) {
                for(int j = 0; j < game.getBoardSize(); j++) {
                    if(!game.isPlaceTaken(i, j)) {
                        game.setElement(Mark.X, i, j);

                        maxEvaluation = Math.max(maxEvaluation, miniMax(game, depth - 1, false));
                        game.setElement(Mark.BLANK, i, j);
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

                        minEvaluation = Math.min(minEvaluation, miniMax(game, depth - 1, true));
                        game.setElement(Mark.BLANK, i, j);
                    }
                }
            }
            return minEvaluation;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(3, 3);
        // MiniMax AI = new MiniMax();
        // Human human = new Human();
        int[] result;

        // game.getBoard().displayBoard();

        // ruch gracza
        game.setElement(Mark.O, 2, 0);
        game.setElement(Mark.O, 1, 0);
        // game.getBoard().displayBoard();

        // ruch minimaxa
        result = MiniMax.makeMove(game, Mark.BLANK);
        game.setElement(Mark.X, result[0], result[1]);
        // result = AI.makeMove(game, Mark.BLANK);
        // game.setElement(Mark.X, result[0], result[1]);
        game.getBoard().displayBoard();
    }
}