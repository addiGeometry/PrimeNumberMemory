package game;

public interface DevMemoryAPI extends MemoryAPI{

    public void setPunkteStand(PlayerLogic playerLogic, int punkte);

    public void setBoard(Card[][] devBoard);

    /**
     * Dev-Method to deactivate card
     */
    public void deactivateCard(Coordinates a);

    public Status getStatus();

    public String getFirstPlayer();

    public Card getCard(int i, int j);

    public void setPlayerScore(PlayerLogic playerLogic, int i);
}
