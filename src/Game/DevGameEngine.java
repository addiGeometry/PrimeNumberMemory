package Game;

interface DevGameEngine extends MemoryAPI, GameEngine{
    /**
     * @param board Übergebe modifiziertes Board (für Testzwecke)
     */
    void setBoard(Card[][] board);

    void setPunkteStand(Player player1, int i);

    boolean isDraw(Player player2);
}
