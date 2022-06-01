package Game;

public class Memory implements MemoryAPI{

    private final Board board;
    private final GameEngine game;
    private final Player firstPlayer, secondPlayer;

    public Memory(BoardGenerator boardGen, Player p1, Player p2){
        
        board = new BoardImplementation(boardGen.generateBoard6x6());
        game = new GameEngineImplementation(p1,p2);
        firstPlayer = p1;
        secondPlayer = p2;
    }

    @Override
    public boolean flip(Player player, Coordinate firstCard, Coordinate secondCard) throws NotYourTurnException, CardsGoneException {
        return false;
    }

    @Override
    public void surrender(Player player) {
    }

    @Override
    public Card[][] getBoard() {
        return new Card[0][];
    }

    public int hasScore(Player player) {
        return 0;
    }

    @Override
    public boolean isWinner(Player p1) {
        return false;
    }

    @Override
    public boolean isDraw() {
        return false;
    }

}
