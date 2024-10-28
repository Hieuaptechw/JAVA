package Exceptions;

public class InvalidCustomerIdException extends RuntimeException {
    public InvalidCustomerIdException(String message) {
        super(message);
    }

    public InvalidCustomerIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCustomerIdException(Throwable cause) {
        super(cause);
    }

    public InvalidCustomerIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidCustomerIdException() {
    }
}
