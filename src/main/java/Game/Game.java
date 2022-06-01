package Game;

import java.util.Scanner;

import Players.MiniMax;

// import Players.*;

public class Game {
    private Board board;
    private final int BOARD_SIZE;
    private final int WIN_CONDITION;
    private int possibleMoves;

    public Game(int boardSize, int winCondition) {
        if(boardSize < winCondition)
            System.exit(1); // trochę na łatwiznę

        BOARD_SIZE = boardSize;
        WIN_CONDITION = winCondition;
        possibleMoves = BOARD_SIZE * BOARD_SIZE;
        board = new Board(BOARD_SIZE);
    }

    public Game(Game other) {
        this.BOARD_SIZE = other.BOARD_SIZE;
        this.WIN_CONDITION = other.WIN_CONDITION;
        this.board = new Board(other.getBoard());
        this.possibleMoves = other.possibleMoves;
    }

    public boolean setElement(Mark element, int row, int col) {
        boolean validMove = board.setElement(element, row, col);
        if(validMove)
            possibleMoves -= 1;
        return validMove;
    }

    public int getPossibleMoves() {
        return possibleMoves;
    }

    public Board getBoard() {
        return board;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    // sprawdzenie wszystkich możliwosći czy ustawiono wymaganą ilość jednego znaku w poziomie
    private boolean checkHorizontal(Mark player) {
        for(int i = 0; i < BOARD_SIZE; ++i) {
            int counter = 0;
            for(int j = 0; j < BOARD_SIZE; ++j) {
                if(board.getElement(i, j) == player)
                    counter += 1;
                else if(board.getElement(i, j) != player)
                    counter = 0;
                else if(counter == 0 && ((BOARD_SIZE - j) < WIN_CONDITION))
                    break;
                if(counter >= WIN_CONDITION)
                    return true;
            }
        }
        return false;
    }

    // sprawdzenie wszystkich możliwosći czy ustawiono wymaganą ilość jednego znaku w pionie
    private boolean checkVertical(Mark player) {
        for(int j = 0; j < BOARD_SIZE; ++j) {
            int counter = 0;
            for(int i = 0; i < BOARD_SIZE; ++i) {
                if(board.getElement(i, j) == player)
                    counter += 1;
                else if(board.getElement(i, j) != player)
                    counter = 0;
                else if(counter == 0 && ((BOARD_SIZE - j) < WIN_CONDITION))
                    break;
                if(counter >= WIN_CONDITION)
                    return true;
            }
        }
        return false;
    }

    // sprawdzenie wszystkich możliwosći czy ustawiono wymaganą ilość jednego znaku w po skosie \
    private boolean checkDiagonalBackSlash(Mark player) {
        int l = 0;
        int counter = 0;
        // backslash \
        for(int i = 0; i <= BOARD_SIZE - WIN_CONDITION; ++i) {
            for(int j = 0; j <= BOARD_SIZE - WIN_CONDITION; ++j) {
                l = i;
                for(int k = j; k < (WIN_CONDITION + j); ++k) {
                    if(board.getElement(l, k) == player)
                        counter += 1;
                    else if(board.getElement(l, k) != player)
                        counter = 0;
                    l++;
                }
                if(counter >= WIN_CONDITION)
                    return true;
            }
        }
        return false;
    }

    // sprawdzenie wszystkich możliwosći czy ustawiono wymaganą ilość jednego znaku w po skosie /
    private boolean checkDiagonalSlash(Mark player) {
        int l = 0;
        int counter = 0;
        // slash /
        for(int i = 0; i <= BOARD_SIZE - WIN_CONDITION; ++i) {
            for(int j = 0; j <= BOARD_SIZE - WIN_CONDITION; ++j) {
                l = i;
                for(int k = j + WIN_CONDITION - 1; k >= 0; --k) {
                    if(board.getElement(l, k) == player)
                        counter += 1;
                    else if(board.getElement(l, k) != player) {
                        counter = 0;
                        break;
                    }
                    if(counter >= WIN_CONDITION)
                        return true;
                    l++;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(Mark player) {
        return checkDiagonalSlash(player) || checkDiagonalBackSlash(player);
    }

    public boolean checkWin(Mark player) {
        return checkDiagonal(player) || checkHorizontal(player) || checkVertical(player);
    }

    public boolean checkIfAnyMovesAvailable() {
        return possibleMoves > 0;
        // for(int i = 0; i < BOARD_SIZE; ++i)
        //     for(int j = 0; j < BOARD_SIZE; ++j)
        //         if(board.getElement(i, j) == Mark.BLANK)
        //             return false;
        // return true;
    }

    public static void main(String[] args) {
        
        Game game = new Game(3, 3);

        Game gameCopy = new Game(game);

        // game.getBoard().displayBoard();

        // MiniMax AI = new MiniMax();

        Scanner in = new Scanner(System.in);
        int row = 1;
        int col = 1;

        game.setElement(Mark.O, row, col);

        game.getBoard().displayBoard();

        gameCopy.getBoard().displayBoard();

        // int[] result = AI.makeMove(game);
        // game.getBoard().displayBoard();


        // System.out.println(result[0] + " " + result[1]);

        // game.setElement(Mark.O, result[0], result[1]);
        // game.getBoard().displayBoard();


        // while(game.getPossibleMoves() > 0) {
        //     System.out.print("Podaj wiersz i kolumnę (np. 1 2): ");
        //     row = in.nextInt();
        //     col = in.nextInt();
        //     while(!game.setElement(currentPlayer, row, col)) {
        //         System.out.print("Złe dane. Podaj wiersz i kolumnę (np. 1 2): ");
        //         row = in.nextInt() + 1;
        //         col = in.nextInt() + 1;
        //     }
        //     game.getBoard().displayBoard();
        // }
        in.close();
    }
}
