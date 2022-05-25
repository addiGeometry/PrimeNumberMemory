package Game;

public interface GameEngine extends MemoryAPI{
    boolean startGame(Player player1, Player player2);

    /**
     * @param name Name des Spielers
     * @param player Spieler P1 oder P2
     */
    void assignPlayer(String name, Player player);

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

    // void subscribeChangeListener(LocalBoardChangeListener changeListener); TODO
}
