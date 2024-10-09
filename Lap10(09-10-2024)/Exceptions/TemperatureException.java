package Exceptions;

public abstract class TemperatureException extends Exception {
    private final int temperature;

    public TemperatureException(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}
