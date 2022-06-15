package Game;

public interface DevMemoryAPI extends MemoryAPI{

    public void setPunkteStand(Player player, int punkte);

    public void setBoard(Card[][] devBoard);

    public Status getStatus();

    public String getFirstPlayer();
}
