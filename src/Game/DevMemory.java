package Game;

public class DevMemory extends Memory implements DevMemoryAPI{
    private Card[][] devBoard;

    public Status getStatus() {
        return status;
    }

    @Override
    public String getFirstPlayer() {
        return order.get(Player.P1);
    }

    public DevMemory(Player p1, Player p2, String name){
        super(p1, p2, name);
        devBoard = boardGen.generateBoard6x6();
    }


    @Override
    public void surrender(Player player) {

    }

    @Override
    public Card[][] getBoard() {
        return devBoard;
    }

    @Override
    public void setPunkteStand(Player player, int punkte) {
    }

    @Override
    public void setBoard(Card[][] devBoard) {
        this.devBoard = devBoard;
    }

    public void deactivateCard(Coordinates a) {
        devBoard[boardParser.parseLetterCoord(a)][boardParser.parseNumberCoord(a)].deactivate();
    }
}
