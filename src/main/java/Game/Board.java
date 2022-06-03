package Game;

// klasa modelująca tablicę do gry w kółko i krzyżyk
public class Board {
    private final int SIZE;
    private Mark[][] board;

    public Board(int size) {
        this.SIZE = size;
        board = new Mark[SIZE][SIZE];
        
        for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) 
                this.board[row][col] = Mark.BLANK;
        }
    }

    public Board(Board other) {
        this.SIZE = other.SIZE;
        this.board = other.getBoardCopy();
    }

    public Mark[][] getBoardCopy() {
        Mark [][] boardCopy = new Mark[board.length][];
        for(int i = 0; i < board.length; i++)
            boardCopy[i] = board[i].clone();
        return boardCopy;
    }

    public int getBoardSize() {
        return SIZE;
    }

    public void displayBoard() {
        System.out.print("\n");
        for(int col = 0; col < SIZE; ++col)
            System.out.print("----");
        System.out.print("-\n");
        for(int row = 0; row < SIZE; ++row) {
            System.out.print("|");
            for(int col = 0; col < SIZE; ++col)
                System.out.print(" " + getElement(row, col).toString() + " |");
            System.out.print("\n");
            for(int col = 0; col < SIZE; ++col)
                System.out.print("----");
            System.out.print("-\n");
        }
        System.out.print("\n");  
    }

    public Mark getElement(int row, int col) {
        assert(row < SIZE && row >= 0 && col < SIZE && col >= 0);
        return board[row][col];
    }

    public boolean setElement(Mark element, int row, int col) {
        if(row >= SIZE || row < 0 || col >= SIZE || col < 0 || getElement(row, col).isTaken())
            return false;
        
        board[row][col] = element;
        return true;
    }

    public boolean changeElement(Mark element, int row, int col) {
        if(row >= SIZE || row < 0 || col >= SIZE || col < 0)
            return false;
        
        board[row][col] = element;
        return true;
    }

    public static void main(String[] args) {
        Board board = new Board(4);

        board.setElement(Mark.X, 2, 1);
        board.setElement(Mark.X, 3, 2);
        board.setElement(Mark.X, 0, 3);

        board.displayBoard();
    }
}