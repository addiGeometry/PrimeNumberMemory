package Game;

public class GameEngineImplementation implements GameEngine{

    public GameEngineImplementation(Player first, Player second){

    }

    @Override
    public boolean startGame(Player player1, Player player2) {
        return false;
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
}
