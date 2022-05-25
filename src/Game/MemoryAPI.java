package Game;


public interface MemoryAPI {
    /**
     * @param x Karte 1
     * @param y und Karte 2 die umgedeckt werden
     * @param player Der Spieler der umdecken will
     * @return Gib true zurück wenn es der siegende Zug war
     * @throws NotYourTurnException Der Spieler war nicht am Zug
     * @throws GameException Die Karten sind schon vom Feld
     */
    boolean flip(Player player, Position x, Position y) throws NotYourTurnException, GameException;

    /**
     * @param player Der Spieler der aufgibt
     */
    void surrender(Player player);

    /**
     * @return gebe das Aktuelle Feld zurück
     */
    Card[][] getBoard();

}
