package Game;

public interface DevMemoryAPI extends MemoryAPI{

    public void setPunkteStand(Player player, int punkte);

    public void setBoard(Card[][] devBoard);

    /**
     * Dev-Method to deactivate card
     */
    public void deactivateCard(Coordinates a);

    public Status getStatus();

    public String getFirstPlayer();
}
