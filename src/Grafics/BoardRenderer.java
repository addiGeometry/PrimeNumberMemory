package Grafics;

import Game.Board;
import Game.Card;
import Game.Coordinates;

public interface BoardRenderer{
    public String renderOpenBoard(Card[][] board, Coordinates a, Coordinates b);

    public String renderClosedBoard(Card[][] toRender, Coordinates a, Coordinates b);
}
