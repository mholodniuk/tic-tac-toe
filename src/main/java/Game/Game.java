package Game;



public class Game {
    private Board board;
    private final int BOARD_SIZE;
    private final int WIN_CONDITION;

    public Game(int boardSize, int winCondition) {
        BOARD_SIZE = boardSize;
        WIN_CONDITION = winCondition;
        board = new Board(BOARD_SIZE);
    }

    public boolean setElement(Cell element, int row, int col) {
        return board.setElement(element, row, col);
    }

    // sprawdzenie wszystkich możliwosći czy ustawiono wymaganą ilość jednego znaku w poziomie
    public boolean checkHorizontal(Cell player) {
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
    public boolean checkVertical(Cell player) {
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

    public boolean checkDiagonal(Cell player) {
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
                    //System.out.println(l + " <- l, k -> " + k);
                    l++;
                }
                if(counter >= WIN_CONDITION)
                    return true;
            }
        }
        counter = 0;
        // slash /
        for(int i = 0; i <= BOARD_SIZE - WIN_CONDITION; ++i) {
            for(int j = 0; j <= BOARD_SIZE - WIN_CONDITION; ++j) {
                l = i;
                for(int k = j + WIN_CONDITION - 1; k >= 0; --k) {
                    // System.out.println(l + " <- l, k -> " + k);
                    if(board.getElement(l, k) == player)
                        counter += 1;
                    else if(board.getElement(l, k) != player) {
                        counter = 0;
                        break;
                    }
                    // System.out.println("counter: " + counter);
                    if(counter >= WIN_CONDITION)
                        return true;
                    l++;
                }
                
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Game game = new Game(4, 3);

        // game.setElement(Cell.X, 3, 4);
        // game.setElement(Cell.X, 4, 3);

        game.setElement(Cell.X, 1, 3);
        game.setElement(Cell.X, 2, 2);
        game.setElement(Cell.X, 3, 4);

        game.board.displayBoard();

        if(game.checkDiagonal(Cell.X))
            System.out.println("Wygrał X");
    }
}
