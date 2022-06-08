package Game;

import java.util.HashMap;

public class Memory implements MemoryAPI, Board{
    private final static String default_name="noname";
    protected final String localPlayerName;
    private final BoardGenerator boardGen;
    protected Player localPlayer, remotePlayer;
    private String remotePlayerName;

    protected HashMap<Player, String> order = new HashMap<>();

    private boolean localWon;

    protected Status status = Status.START;

    private Card[][] board;

    public Memory(Player p1, Player p2, String name){
        this.localPlayerName = name;
        this.boardGen = new BoardGeneratorImplementation();
        this.board = boardGen.generateBoard6x6();
    }

    //Keine Schnittstellenfunktion nur intern
    public void pickSides() throws StatusException {
        //Sollte nicht passieren weil es ein interner Call ist:
        if(this.status != Status.START){
            throw new StatusException("Das sollte zwar nicht passieren, aber die Methode, die entscheidet wer Beginnt wurde zum falschen Zeitpunkt aufgerufen");
        }
        String firstplayer;
        String secondplayer;
        if(Math.random() < 0.5){
            firstplayer = localPlayerName;
            secondplayer = remotePlayerName;
        }
        else{
            firstplayer = remotePlayerName;
            secondplayer = localPlayerName;
        }
        this.order.put(Player.P1, firstplayer);
        this.order.put(Player.P2, secondplayer);

        this.status = Status.P1_Turn;
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

    @Override
    public Card[][] getCurrentBoard() {
        return new Card[0][];
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int removeCards(Coordinate x, Coordinate y) {
        return 0;
    }
}
