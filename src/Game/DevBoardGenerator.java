package Game;

public interface DevBoardGenerator extends BoardGenerator{
    //Set Custom Dev-Board
    public void setBoard(Card[][] devBoard);

    public Card[][] generateDevBoard6x6();
}
