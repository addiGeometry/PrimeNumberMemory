package Game;

public class Player{
    private final String name;
    private final PlayerLogic position;

    private int playerStack;

    public Player(String name, PlayerLogic position){
        this.name = name;
        this.position = position;
        this.playerStack = 0;
    }

    public int getScore(){
        return playerStack;
    }

    public void incScore(){
        playerStack++;
    }
}
