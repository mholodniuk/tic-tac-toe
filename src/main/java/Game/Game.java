package Game;

public class Game {
    private Board board;
    private static final int SIZE = 3;

    public Game() {
        board = new Board(SIZE);
    }
}
