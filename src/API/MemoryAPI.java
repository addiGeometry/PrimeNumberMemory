package API;

import Entities.Card;
import Exceptions.BadParameterException;
import Exceptions.NotYourTurnException;

public interface MemoryAPI {
    //game interactions
    void flip(int x, int y) throws NotYourTurnException, BadParameterException;

    void surrender();

    Card[][] getBoard();
}
