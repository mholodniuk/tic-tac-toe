package Game;

import java.util.ArrayList;


public class Board {

    final int size;
    ArrayList<Cell> board;

    public Board(int size) {
        this.size = size;
        board = new ArrayList<Cell>(size * size);
        
        for(int row = 0; row < size * size; ++row)
            board.add(Cell.BLANK);
    }

    public void displayBoard() {
        System.out.print("\n");
        for(int col = 0; col < size; ++col)
            System.out.print("----");
        System.out.print("-\n");
        for(int row = 0; row < size; ++row) {
            System.out.print("|");
            for(int col = 0; col < size; ++col)
                System.out.print(" " + getElement(row, col).toString() + " |");
            System.out.print("\n");
            for(int col = 0; col < size; ++col)
                System.out.print("----");
            System.out.print("-\n");
        }
        System.out.print("\n");  
    }

    public Cell getElement(int row, int col) {
        return board.get(row * size + col);
    }

    public boolean setElement(Cell element, int row, int col) {
        row -= 1; col -= 1;
        if(row >= size || row <= 0 || col >= size || col <= 0 || getElement(row, col).isMarked())
            return false;
        board.set(row * size + col, element);
        return true;
    }

    public static void main(String[] args) {
        Board board = new Board(6);
        board.setElement(Cell.X, 3, 5);
        board.displayBoard();
    }
}