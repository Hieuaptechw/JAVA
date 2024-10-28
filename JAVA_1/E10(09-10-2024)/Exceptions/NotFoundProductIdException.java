package Exceptions;

public class NotFoundProductIdException extends RuntimeException {
    public NotFoundProductIdException(String message) {
        super(message);
    }
}
