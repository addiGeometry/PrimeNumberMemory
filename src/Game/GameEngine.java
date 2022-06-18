package Game;

public interface GameEngine{
    boolean startGame(PlayerLogic playerLogic1, PlayerLogic playerLogic2);

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
     * @param playerLogic Gebe Anzahl der Paare von einem Spieler aus
     * @return  Anzahl Paare
     */
    int hasScore(PlayerLogic playerLogic);

    /**
     * @return Wahr wenn Spieler gewonnen hat
     */
    boolean hasWon(PlayerLogic playerLogic);

    /**
     * @return Wahr wenn Spieler gewonnen hat
     */
    boolean hasLost(PlayerLogic playerLogic);

    boolean surrender();

    // void subscribeChangeListener(LocalBoardChangeListener changeListener); TODO
}
