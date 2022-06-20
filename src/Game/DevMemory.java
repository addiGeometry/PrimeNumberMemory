package Game;

public class DevMemory extends Memory implements DevMemoryAPI{
    private Card[][] devBoard;

    public Status getStatus() {
        return status;
    }

    @Override
    public String getFirstPlayer() {
        if(order.get(localPlayerName) == PlayerLogic.P1) return localPlayerName;
        else return remotePlayerName;
    }

    public DevMemory(PlayerLogic p1, PlayerLogic p2, String name){
        super(p1, p2, name);
        devBoard = boardGen.generateBoard6x6();
    }

    @Override
    public void setPunkteStand(PlayerLogic playerLogic, int punkte) {
    }

    @Override
    public void setPlayerScore(PlayerLogic playerLogic, int i){
        for(int r=0; r<i; r++){
            assign.get(playerLogic).incScore();
        }
    }

    @Override
    public void setBoard(Card[][] devBoard) {
        this.devBoard = devBoard;
    }

    public void deactivateCard(Coordinates a) {
        devBoard[boardParser.parseLetterCoord(a)][boardParser.parseNumberCoord(a)].deactivate();
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
        }

        boolean hasWon = this.hasWon(playerLogic);

        if(hasWon){
            System.out.println("Glückwunsch! Spieler: " + this.localPlayerName + " hat nach " + zug + " Zügen gewonnen");
            this.status = Status.ENDED;
            //TODO
            //if(this.localSymbol == piece) this.localWon = true;
        }
        else{
            this.status = this.status == Status.P1_Turn ? Status.P2_Turn : Status.P1_Turn;
            //Maybe?
            //TODO
        }

        //TODO
        //Protokoll aka. Tell the other side
        return hasWon;
    }

    @Override
    protected Card flipHelp(Coordinates coord){
        int vertical = boardParser.parseLetterCoord(coord);
        int horizontal = boardParser.parseNumberCoord(coord);

        return devBoard[vertical][horizontal];
    }

    @Override
    public Card getCard(int x, int y){
        return this.devBoard[x][y];
    }
    @Override
    protected Card parseNgetCard(Coordinates coord){
        return this.getCard(this.boardParser.parseLetterCoord(coord),this.boardParser.parseNumberCoord(coord));
    }
}
