package Game;

interface DevGameEngine extends MemoryAPI, GameEngine{
    /**
     * @param board Übergebe modifiziertes Board (für Testzwecke)
     */
    void setBoard(Card[][] board);
}
