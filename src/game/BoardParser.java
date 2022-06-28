package game;

public interface BoardParser {
    public int parseLetterCoord(Coordinates c);

    public int parseNumberCoord(Coordinates c);

    Coordinates reconstructCard(int v, int h) throws GameException;
}
