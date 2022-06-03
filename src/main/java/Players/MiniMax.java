package Players;

import Game.*;

public class MiniMax{
    
    private static final int MAX_DEPTH = 5;

    private MiniMax() {
    }

    public static void makeMove(Game game, Mark mark) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                if(!game.isPlaceTaken(i, j)) {
                    game.setElement(Mark.X, i, j);
                    int moveValue = miniMax(game, MAX_DEPTH, false);
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
    }

    private static int miniMax(Game game, int depth, boolean isMaximizing) {
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
                        int currentMiniMaxValue = miniMax(game, depth - 1, false);
                        if(!game.changeElement(Mark.BLANK, i, j))
                            System.out.println("Nie udało się zamienić wartości");
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
                        if(!game.changeElement(Mark.BLANK, i, j))
                            System.out.println("Nie udało się zamienić wartości");
                        minEvaluation = Math.min(minEvaluation, currentMiniMaxValue);
                    }
                }
            }
            return minEvaluation;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(3, 3);

        // ruch minimaxa
        MiniMax.makeMove(game, Mark.BLANK);

        // game.setElement(Mark.X, result[0], result[1]);
        game.getBoard().displayBoard();
    }
}