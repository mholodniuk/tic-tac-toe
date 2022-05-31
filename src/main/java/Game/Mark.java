package Game;

public enum Mark {
    X('X'),
    O('O'),
    BLANK(' ');

    private final char mark;

    Mark(char symbol) {
        this.mark = symbol;
    }

    public boolean isTaken() {
        return this != BLANK;
    }

    public char getMark() {
        return this.mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
