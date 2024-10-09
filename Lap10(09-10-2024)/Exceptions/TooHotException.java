package Exceptions;

public class TooHotException extends TemperatureException {
    public TooHotException(int temperature) {
        super(temperature);
    }
}
