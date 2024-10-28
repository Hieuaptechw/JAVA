package ExceptionValidation;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(){;}
    public InvalidQuantityException(String message){
        super(message);
    }
    public InvalidQuantityException(String message,Throwable cause){
        super(message, cause);
    }
}
