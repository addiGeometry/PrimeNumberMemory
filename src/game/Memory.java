package game;

import grafics.BoardRenderer;
import grafics.BoardRendererImplementation;
import network.GameSessionEstablishedListener;

import java.util.HashMap;
import java.util.List;

public class Memory implements MemoryAPI, Board, GameSessionEstablishedListener {
    private final static String DEFAULT_NAME="defo";

    protected final BoardGenerator boardGen;
    protected final BoardParser boardParser;

    private final BoardRenderer render;

    protected final String localPlayerName;
    protected String remotePlayerName;

    protected PlayerLogic localPlayerLogic, remotePlayerLogic;

    private Player localPlayer, remotePlayer;

    protected HashMap<String, PlayerLogic> order = new HashMap<>();
    protected HashMap<PlayerLogic, Player> assign = new HashMap<>();

    private PNMProtocolEngine protocolEngine;


    private boolean localWon;
    public static int zug;

    protected Status status = Status.START;

    private Card[][] board;
    private static final int BOARDSIZE=6;


    public Memory(String name){
        this.localPlayerName = name;
        this.remotePlayerName = DEFAULT_NAME;

        this.render = new BoardRendererImplementation();
        this.boardGen = new BoardGeneratorImplementation();
        this.boardParser = new BoardParserImplementation();
        this.board = boardGen.generateBoard6x6();
        this.pickSides();

        this.localPlayer = new Player(localPlayerName, order.get(localPlayerName));
        this.remotePlayer = new Player(remotePlayerName, order.get(remotePlayerName));
        this.assign.put(order.get(localPlayerName), localPlayer);
        this.assign.put(order.get(remotePlayerName), remotePlayer);
    }

    private void pickSides(){
        /** y//Sollte nicht passieren weil es ein interner Call ist:
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
            System.out.println(this.localPlayerName + ": set " + playerLogic  + " - not won, new status " + this.status);
        }

        //TODO
        //Protokoll aka. Tell the other side
        if(this.localPlayerLogic == playerLogic && this.protocolEngine != null){
            this.protocolEngine.flip(localPlayerLogic, firstCard, secondCard);
        }
        else{
            this.notifyBoardChanged();
        }

        return hasWon;
    }

    public boolean flip(Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        return this.flip(localPlayerLogic,firstCard,secondCard);
    }

    private String mapOrderBw(PlayerLogic playerLogic){
        if(playerLogic == order.get(localPlayerName)) return localPlayerName;
        else return remotePlayerName;
    }

    protected Card flipHelp(Coordinates coord){
        int vertical = boardParser.parseLetterCoord(coord);
        int horizontal = boardParser.parseNumberCoord(coord);

        return board[vertical][horizontal];
    }


    @Override
    public boolean surrender(PlayerLogic playerLogic) throws StatusException{
        if(this.status == Status.ENDED) throw new StatusException("the game is over already!");

        this.status = Status.ENDED;
        //TODO
        //Notify Winner on the other side
        return false;
    }
    /************************************************************************************************************
     ***                                      construction methods                                            ***
     ***********************************************************************************************************/
    protected boolean hasWon(PlayerLogic playerLogic) {
        if(assign.get(playerLogic).getScore() > 8) return true;
        return false;
    }
    @Override
    public boolean hasWon() {
        return this.status == Status.ENDED && this.localWon;
    }

    @Override
    public boolean hasLost() {
        return this.status == Status.ENDED && !this.localWon;
    }

    public void setProtocolEngine(PNMProtocolEngine protocolEngine) {
        this.protocolEngine = protocolEngine;
        this.protocolEngine.subscribeGameSessionEstablishedListener(this);
    }

    public int hasScore(PlayerLogic playerLogic) {
        return assign.get(playerLogic).getScore();
    }

    @Override
    public String generateBoard() {
        return render.renderClosedBoard(this.getCurrentBoard(), Coordinates.A1, Coordinates.A2);
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

    public Card getCard(int x, int y){
        return this.board[x][y];
    }

    protected Card parseNgetCard(Coordinates coord){
        return this.getCard(this.boardParser.parseLetterCoord(coord),this.boardParser.parseNumberCoord(coord));
    }
    /************************************************************************************************************
     ***                                              observed                                                ***
     ***********************************************************************************************************/

    private List<LocalBoardChangeListener> boardChangeListenerList;

    @Override
    public void subscribeChangeListener(LocalBoardChangeListener changeListener){
        this.boardChangeListenerList.add(changeListener);
    }

    private void notifyBoardChanged() {
        //are there any listeners?
        if(this.boardChangeListenerList == null || this.boardChangeListenerList.isEmpty()) return;
        //yes there are - create new thread and inform them
        (new Thread(new Runnable() {
            @Override
            public void run() {
                for(LocalBoardChangeListener listener : boardChangeListenerList){
                    listener.changed();
                }
            }
        })).start();
    }


    /************************************************************************************************************
     ***                                              listener                                                ***
     ***********************************************************************************************************/


    @Override
    public void gameSessionEstablished(boolean oracle, String partnerName) {
        System.out.println(this.localPlayerName + ": gameSessionEstablished with " + partnerName + " | " + oracle);

        this.localPlayerLogic = oracle ? PlayerLogic.P1 : PlayerLogic.P2;
        this.remotePlayerLogic = this.localPlayerLogic == PlayerLogic.P1 ? PlayerLogic.P2 : PlayerLogic.P1;
        this.remotePlayerName = partnerName;

        // O always starts
        this.status = Status.P1_Turn;
    }

}
