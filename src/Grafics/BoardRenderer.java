package Grafics;

import Game.Board;
import Game.Card;
import Game.Coordinates;

public interface BoardRenderer{

    public void renderBoard();

    public String renderOpenBoard(Card[][] board, Coordinates a, Coordinates b);
}
