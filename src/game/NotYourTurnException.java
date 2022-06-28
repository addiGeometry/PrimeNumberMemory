package game;

public class NotYourTurnException extends Exception{
    NotYourTurnException(String message){
        super(message);
    }
}
