package Game;


public interface MemoryAPI {
    /**
     * @param player Der Spieler, der umdecken will
     * @param firstCard Karte 1
     * @param secondCard und Karte 2 die umgedeckt werden sollen.
     * @return Gib true zurück, wenn es der siegende Zug war
     * @throws NotYourTurnException Der Spieler war nicht am Zug
     * @throws GameException Die Karten sind schon vom Feld
     */
    boolean flip(Player player, Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException;

    boolean flip(Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException;
    /**
     * @param player Der Spieler der aufgibt
     */
    void surrender(Player player);

    /**
     * @return gebe das Aktuelle Feld zurück
     */
    Card[][] getBoard();

    int hasScore(Player player);

    boolean isWinner(Player p1);

    boolean isDraw();

}
