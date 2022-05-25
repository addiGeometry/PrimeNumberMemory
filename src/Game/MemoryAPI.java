package Game;


public interface MemoryAPI {
    //game interactions
    boolean flip(int x, int y) throws NotYourTurnException, BadParameterException;

    void surrender();

    Card[][] getBoard();

}
