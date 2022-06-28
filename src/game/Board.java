package game;

public interface Board{

    /**
     * Gebe das Spielbrett mit den übrigen Karten zurück
     * @return Aktuelles Spielfeld
     */
    public Card[][] getCurrentBoard();

    String generateBoard();

    /**
     * @return Wahr, wenn alle Paare noch im Spiel sind
     */
    public boolean isFull();

    /**
     * @return Wahr, wenn keine Paare mehr im Spiel sind
     */
    public boolean isEmpty();


    /**
     * @return gibt den Spielerscore von
     * @param playerLogic zurück
     */
    int hasScore(PlayerLogic playerLogic);

    boolean hasLost();

    boolean hasWon();


    /**
     * Subscribe for changes
     * @param changeListener
     */
    void subscribeChangeListener(LocalBoardChangeListener changeListener);
}
