package Game;

public class DevBoardGeneratorImpl extends BoardGeneratorImplementation implements DevBoardGenerator{

    private Card[][] devBoard;

    public DevBoardGeneratorImpl(Card[][] developerBoard){
        this.devBoard = developerBoard;
    }

    @Override
    public void setBoard(Card[][] devBoard) {
        this.devBoard = devBoard;
    }

    @Override
    public Card[][] generateDevBoard6x6() {
        return devBoard;
    }


}
