package Game;

public class DevMemory extends Memory implements DevMemoryAPI{

    private Card[][] devBoard;

    public DevMemory(BoardGenerator classicGen,DevBoardGenerator devBoardGenerator, Player p1, Player p2){
        super(p1, p2, );
    }

    @Override
    public boolean flip(Player player, Coordinate firstCard, Coordinate secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException {
        return false;
    }

    @Override
    public void surrender(Player player) {

    }

    @Override
    public Card[][] getBoard() {
        return new Card[0][];
    }

    @Override
    public void setPunkteStand(Player player, int punkte) {
    }

    @Override
    public void setBoard(Card[][] devBoard) {
        this.devBoard = devBoard;
    }
}
