package API;

public class BadParameterException extends Exception{
    BadParameterException(String message){
        super(message);
    }
}
