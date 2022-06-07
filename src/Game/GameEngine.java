package Game;

public interface GameEngine{
    boolean startGame(Player player1, Player player2);

    /**
     * @return Spiel Status
     */
    Status getStatus();

    /**
     * @return Wenn aktiv, kann Karte geflipped werden
     */
    boolean isActive();

    /**
     *
     * @param player Gebe Anzahl der Paare von einem Spieler aus
     * @return  Anzahl Paare
     */
    int hasScore(Player player);

    /**
     * @return Wahr wenn Spieler gewonnen hat
     */
    boolean hasWon(Player player);

    /**
     * @return Wahr wenn Spieler gewonnen hat
     */
    boolean hasLost(Player player);

    boolean surrender();

    // void subscribeChangeListener(LocalBoardChangeListener changeListener); TODO
}
