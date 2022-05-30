package Game;

public class Board {

    private final int SIZE;
    private Cell[][] board;

    public Board(int size) {
        this.SIZE = size;
        board = new Cell[SIZE][SIZE];
        
        for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) 
                board[row][col] = Cell.BLANK;
        }
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

    public Cell getElement(int row, int col) {
        assert(row < SIZE && row >= 0 && col < SIZE && col >= 0);
        return board[row][col];
    }

    public boolean setElement(Cell element, int row, int col) {
        row -= 1; col -= 1;
        if(row >= SIZE || row < 0 || col >= SIZE || col < 0 || getElement(row, col).isTaken())
            return false;
        
        board[row][col] = element;
        return true;
    }

    public static void main(String[] args) {
        Board board = new Board(3);

        board.setElement(Cell.X, 1, 1);
        board.setElement(Cell.X, 2, 2);
        board.setElement(Cell.X, 3, 3);

        board.displayBoard();
    }
}