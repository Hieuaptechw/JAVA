package Exceptions;

public class InvalidProducIdException extends RuntimeException {
    public InvalidProducIdException(String message) {
        super(message);
    }
}
