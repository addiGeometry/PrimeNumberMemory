package grafics;

import game.Card;
import game.Coordinates;

public interface BoardRenderer{
    public String renderOpenBoard(Card[][] board, Coordinates a, Coordinates b);

    public String renderClosedBoard(Card[][] toRender, Coordinates a, Coordinates b);
}
