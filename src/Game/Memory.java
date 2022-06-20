package Game;

import Grafics.BoardRenderer;
import Grafics.BoardRendererImplementation;

import java.util.HashMap;
import java.util.List;

public class Memory implements MemoryAPI, Board{
    private final static String DEFAULT_NAME="defo";
    protected final BoardGenerator boardGen;
    protected final BoardParser boardParser;

    private final BoardRenderer boardRenderer;
    protected final String localPlayerName;
    protected PlayerLogic localPlayerLogic, remotePlayerLogic;
    protected String remotePlayerName;

    private Player localPlayer, remotePlayer;

    protected HashMap<String, PlayerLogic> order = new HashMap<>();
    protected HashMap<PlayerLogic, Player> assign = new HashMap<>();


    private boolean localWon;
    public static int zug;

    protected Status status = Status.START;

    private Card[][] board;
    private static final int BOARDSIZE=6;


    public Memory(PlayerLogic p1, PlayerLogic p2, String name){
        this.localPlayerName = name;
        this.remotePlayerName = DEFAULT_NAME;

        this.boardGen = new BoardGeneratorImplementation();
        this.boardParser = new BoardParserImplementation();
        this.board = boardGen.generateBoard6x6();
        this.boardRenderer = new BoardRendererImplementation();
        this.pickSides();

        this.localPlayer = new Player(localPlayerName, order.get(localPlayerName));
        this.remotePlayer = new Player(remotePlayerName, order.get(remotePlayerName));
        this.assign.put(order.get(localPlayerName), localPlayer);
        this.assign.put(order.get(remotePlayerName), remotePlayer);
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
        this.order.put(firstplayer, PlayerLogic.P1);
        this.order.put(secondplayer, PlayerLogic.P2);
        this.status = Status.P1_Turn;
    }

    @Override
    public boolean flip(PlayerLogic playerLogic, Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        if(this.status != Status.P1_Turn && this.status != Status.P2_Turn){
            throw new StatusException("flip called, but wrong status");
        }

        if(playerLogic == null){
            throw new GameException("playerLogic must not be null");
        }

        if( (playerLogic == PlayerLogic.P1) && (this.status == Status.P2_Turn) ){
            throw new NotYourTurnException("Spieler 2 ist am Zug!");
        }

        if( (playerLogic == PlayerLogic.P2) && (this.status == Status.P1_Turn) ){
            throw new NotYourTurnException("Spieler 1 ist am Zug!");
        }

        if(firstCard == secondCard){
            throw new DoublePickException("Du musst zwei verschiedene Karten aufdecken, nicht zwei mal dieselbe.");
        }

        if((this.parseNgetCard(firstCard).isActive() != true) || (this.parseNgetCard(secondCard).isActive() != true)){
            throw new CardsGoneException("Eine oder beide gewählten Karten sind nicht mehr im Spiel. Betrachte noch einmal das Feld und wähle eine andere aus.");
        }
        Card first =  flipHelp(firstCard);
        Card second = flipHelp(secondCard);

        if(first.getValue() == second.getValue()){
            first.deactivate();
            second.deactivate();
            assign.get(playerLogic).incScore();
            System.out.println("Super, Spieler " + assign.get(playerLogic) + " hat erfolgreich das Paar mit der Primzahl " + first.getValue() + " aufgedeckt!");
        }

        boolean hasWon = this.hasWon(playerLogic);

        if(hasWon){
            System.out.println("Glückwunsch! Spieler: " + this.localPlayerName + " hat nach " + zug + " Zügen gewonnen");
            this.status = Status.ENDED;
            if(order.get(localPlayerName) == playerLogic) this.localWon = true;
        }
        else{
            this.status = this.status == Status.P1_Turn ? Status.P2_Turn : Status.P1_Turn;
            //Maybe?
            //TODO
        }

        //TODO
        //Protokoll aka. Tell the other side

        this.notifyBoardChanged();

        return hasWon;
    }

    private String mapOrderBw(PlayerLogic playerLogic){
        if(playerLogic == order.get(localPlayerName)) return localPlayerName;
        else return remotePlayerName;
    }

    protected boolean hasWon(PlayerLogic playerLogic) {
        if(assign.get(playerLogic).getScore() > 8) return true;
        return false;
    }

    protected Card flipHelp(Coordinates coord){
        int vertical = boardParser.parseLetterCoord(coord);
        int horizontal = boardParser.parseNumberCoord(coord);

        return board[vertical][horizontal];
    }

    @Override
    public boolean flip(Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
            return this.flip(localPlayerLogic, firstCard, secondCard);
    }

    @Override
    public void surrender(PlayerLogic playerLogic) throws StatusException{
        if(this.status == Status.ENDED) throw new StatusException("the game is over already!");

        this.status = Status.ENDED;
        //TODO
        //Notify Winner on the other side
    }

    @Override
    public Card[][] getBoard() {
        return new Card[0][];
    }

    public int hasScore(PlayerLogic playerLogic) {
        return assign.get(playerLogic).getScore();
    }

    @Override
    public boolean isDraw() {
        return false;
    }

    @Override
    public Card[][] getCurrentBoard() {
        return this.board;
    }

    @Override
    public boolean isFull() {
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(!this.getCard(i,j).isActive()) return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(this.getCard(i,j).isActive()) return false;
            }
        }
        return true;
    }

    @Override
    public int removeCards(Coordinates x, Coordinates y) {
        return 0;
    }

    public Card getCard(int x, int y){
        return this.board[x][y];
    }

    protected Card parseNgetCard(Coordinates coord){
        return this.getCard(this.boardParser.parseLetterCoord(coord),this.boardParser.parseNumberCoord(coord));
    }
    /************************************************************************************************************
     ***                                              observed                                                ***
     ***********************************************************************************************************/

    private List<localBoardChangeListener> boardChangeListenerList;

    private void subscribeChangeListener(localBoardChangeListener changeListener){
        this.boardChangeListenerList.add(changeListener);
    }

    private void notifyBoardChanged() {
        //are there any listeners?
        if(this.boardChangeListenerList == null || this.boardChangeListenerList.isEmpty()) return;
        //yes there are - create new thread and inform them
        (new Thread(new Runnable() {
            @Override
            public void run() {
                for(localBoardChangeListener listener : boardChangeListenerList){
                    listener.changed();
                }
            }
        })).start();
    }


    /************************************************************************************************************
     ***                                              observer                                                ***
     ***********************************************************************************************************/

    //TODO

}
