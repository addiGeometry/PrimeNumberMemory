package Game;

import java.util.HashMap;

public class Memory implements MemoryAPI, Board{
    private final static String default_name="noname";
    private final BoardGenerator boardGen;
    protected final String localPlayerName;
    protected Player localPlayer, remotePlayer;
    private String remotePlayerName;

    private int localPlayerStack, remotePlayerStack;

    protected HashMap<Player, String> order = new HashMap<>();

    private boolean localWon;

    protected Status status = Status.START;

    private Card[][] board;
    private static final int BOARDSIZE=6;


    public Memory(Player p1, Player p2, String name){
        this.localPlayerName = name;
        this.remotePlayerName = "default";
        this.boardGen = new BoardGeneratorImplementation();
        this.board = boardGen.generateBoard6x6();
        this.pickSides();
    }

    private void pickSides(){
        /** //Sollte nicht passieren weil es ein interner Call ist:
        if(this.status != Status.START){
            throw new StatusException("Das sollte zwar nicht passieren, aber die Methode, die entscheidet wer Beginnt wurde zum falschen Zeitpunkt aufgerufen");
        } */
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
    public boolean flip(Player player, Coordinate firstCard, Coordinate secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        if(this.status != Status.P1_Turn && this.status != Status.P2_Turn){
            throw new StatusException("flip called, but wrong status");
        }

        if(player == null){
            throw new GameException("player must not be null");
        }

        if( (player == Player.P1) && (this.status == Status.P2_Turn) ){
            throw new NotYourTurnException("Spieler 2 ist am Zug!");
        }

        if( (player == Player.P2) && (this.status == Status.P1_Turn) ){
            throw new NotYourTurnException("Spieler 1 ist am Zug!");
        }

        return true;
    }

    private void flipFirstCard(){

    }

    @Override
    public boolean flip(Coordinate firstCard, Coordinate secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
            return this.flip(localPlayer, firstCard, secondCard);
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
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(board[i][j] == null) return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(board[i][j] != null) return false;
            }
        }
        return true;
    }

    @Override
    public int removeCards(Coordinate x, Coordinate y) {
        return 0;
    }
}
