package Exceptions;

public class TooColdException extends TemperatureException {
  public TooColdException(int temperature) {
    super(temperature);
  }
}
