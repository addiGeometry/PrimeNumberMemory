package API;
import Entities.Card;


public interface MemoryAPI {
    //game interactions
    void flip(int x, int y) throws NotYourTurnException, BadParameterException;

    void surrender();

    Card[][] getBoard();
}
