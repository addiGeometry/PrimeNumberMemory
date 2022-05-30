package Game;

public class Memory implements MemoryAPI, GameEngine{

    @Override
    public boolean flip(Player player, Position x, Position y) throws NotYourTurnException, OneCardGoneException, BothCardsGoneException {
        return false;
    }

    @Override
    public boolean startGame(Player player1, Player player2) {
        return false;
    }

    @Override
    public void assignPlayer(String name, Player player) {

    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public int hasScore(Player player) {
        return 0;
    }

    @Override
    public boolean hasWon(Player player) {
        return false;
    }

    @Override
    public boolean hasLost(Player player) {
        return false;
    }

    @Override
    public void surrender(Player player) {
    }

    @Override
    public Card[][] getBoard() {
        return new Card[0][];
    }
}
