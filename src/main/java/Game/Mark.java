package Game;

public enum Mark {
    X('X'),
    O('O'),
    BLANK(' ');

    private final char mark;

    Mark(char mark) {
        this.mark = mark;
    } 

    public boolean isTaken() {
        return this != BLANK;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
