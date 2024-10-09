package Exceptions;

public class NotFoundCustomerIdException extends RuntimeException {
    public NotFoundCustomerIdException(String message) {
        super(message);
    }
}
