package API;

public class NotYourTurnException extends Exception{
    NotYourTurnException(String message){
        super(message);
    }
}
