package Exceptions;

public class NotFoundOrderIdException extends RuntimeException {
    public NotFoundOrderIdException(String message) {
        super(message);
    }
}
