package Game;

public enum Cell {
    X('X'),
    O('O'),
    BLANK(' ');

    private final char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public boolean isMarked() {
        return this != BLANK;
    }

    char getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
