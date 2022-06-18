package Game;


public interface MemoryAPI {
    /**
     * @param playerLogic Der Spieler, der umdecken will
     * @param firstCard Karte 1
     * @param secondCard und Karte 2 die umgedeckt werden sollen.
     * @return Gib true zurück, wenn es der siegende Zug war
     * @throws NotYourTurnException Der Spieler war nicht am Zug
     * @throws GameException Die Karten sind schon vom Feld
     */
    boolean flip(PlayerLogic playerLogic, Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException;

    boolean flip(Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException;
    /**
     * @param playerLogic Der Spieler der aufgibt
     */
    void surrender(PlayerLogic playerLogic);

    /**
     * @return gebe das Aktuelle Feld zurück
     */
    Card[][] getBoard();

    int hasScore(PlayerLogic playerLogic);

    boolean isWinner(PlayerLogic p1);

    boolean isDraw();

}
