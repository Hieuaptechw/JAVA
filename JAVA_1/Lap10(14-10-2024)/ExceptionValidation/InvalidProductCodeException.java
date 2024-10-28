package ExceptionValidation;

public class InvalidProductCodeException extends RuntimeException {
    public InvalidProductCodeException(){;}
    public InvalidProductCodeException(String message){
        super(message);
    }
    public InvalidProductCodeException(String message,Throwable cause){
        super(message, cause);
    }
}
