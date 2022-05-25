package Game;

public class NotYourTurnException extends Exception{
    NotYourTurnException(String message){
        super(message);
    }
}
