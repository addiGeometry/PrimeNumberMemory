package game;

public class FlipCommand {

    private final PlayerLogic player;
    private final Coordinates first,second;

    public FlipCommand(PlayerLogic player, Coordinates first, Coordinates second){
        this.player = player;
        this.first = first;
        this.second = second;
    }

    PlayerLogic getPlayer(){
        return this.player;
    }

    Coordinates getFirst(){
        return this.first;
    }

    Coordinates getSecond(){
        return this.second;
    }

}
