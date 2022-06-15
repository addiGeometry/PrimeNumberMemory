package Game;

public class DevMemory extends Memory implements DevMemoryAPI{
    private Card[][] devBoard;

    public Status getStatus() {
        return status;
    }

    @Override
    public String getFirstPlayer() {
        return order.get(Player.P1);
    }

    public DevMemory(Player p1, Player p2, String name){
        super(p1, p2, name);
    }

    @Override
    public boolean flip(Player player, Coordinate firstCard, Coordinate secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        return false;
    }

    @Override
    public void surrender(Player player) {

    }

    @Override
    public Card[][] getBoard() {
        return devBoard;
    }

    @Override
    public void setPunkteStand(Player player, int punkte) {
    }

    @Override
    public void setBoard(Card[][] devBoard) {
        this.devBoard = devBoard;
    }
}
