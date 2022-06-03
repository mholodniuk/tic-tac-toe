package Game;

// klasa modelująca logikę gry w kółko i krzyżyk
public class Game {
    private Board board;
    private final int BOARD_SIZE;
    private final int WIN_CONDITION;
    private int possibleMoves;

    public Game(int boardSize, int winCondition) {
        if(boardSize < winCondition)
            throw new IllegalArgumentException("Winnig number can not exceed board size!");

        BOARD_SIZE = boardSize;
        WIN_CONDITION = winCondition;
        possibleMoves = BOARD_SIZE * BOARD_SIZE;
        board = new Board(BOARD_SIZE);
    }

    // Deep copy 
    public Game(Game other) {
        this.BOARD_SIZE = other.BOARD_SIZE;
        this.WIN_CONDITION = other.WIN_CONDITION;
        this.board = new Board(other.getBoard());
        this.possibleMoves = other.possibleMoves;
    }

    // metoda pozwalająca graczowi na umieszczenie elementu na tablicy
    // (sprawdza poprawność tej operacji)
    public boolean setElement(Mark element, int row, int col) {
        boolean validMove = board.setElement(element, row, col);
        if(validMove)
            possibleMoves -= 1;
        return validMove;
    }

    // metoda pozwalająca na zmianę wartości podanego elementu
    // (wykorzystywana jedynie przez algorytm MiniMax od symulowania gry)
    public boolean changeElement(Mark element, int row, int col) {
        return board.changeElement(element, row, col);
    }

    // metoda sprawdzająca czy zostały zajęte wszystkie pola - koniec gry (remis)
    public boolean isGameOver() {
        return possibleMoves <= 0;
    }

    // metoda sprawdzająca czy podane miejsce jest zajęte
    public boolean isPlaceTaken(int row, int col) {
        return board.getElement(row, col).isTaken();
    }

    // metoda pobierająca tablicę
    public Board getBoard() {
        return board;
    }

    // metoda pobierająca rozmiar tablicy
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

    // sprawdzenie obu skosów
    private boolean checkDiagonal(Mark player) {
        return checkDiagonalSlash(player) || checkDiagonalBackSlash(player);
    }

    // sprawdzenie czy dany gracz wygrał
    public boolean checkWin(Mark player) {
        return checkDiagonal(player) || checkHorizontal(player) || checkVertical(player);
    }


    // przykładowe działanie aplikacji (+ sprawdzenie poprawności deep copy)
    public static void main(String[] args) {
        
        Game game = new Game(3, 3);

        Game gameCopy = new Game(game);

        int row = 1;
        int col = 1;

        game.setElement(Mark.O, row, col);

        game.getBoard().displayBoard();

        gameCopy.getBoard().displayBoard();

    }
}
